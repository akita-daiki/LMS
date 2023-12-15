package com.book.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.book.config.LoginUserDetails;
import com.book.constants.AvailabilityConstants.AvailabilityType;
import com.book.constants.BookConstants.GenreType;
import com.book.constants.LentFlagRecordsConstants.LentRecordsType;
import com.book.dao.CustomLentManageMapper;
import com.book.dao.CustomLentRecordsMapper;
import com.book.dao.LentManageMapper;
import com.book.dao.LentRecordsMapper;
import com.book.dao.MstBookMapper;
import com.book.dao.UserInfoMapper;
import com.book.dto.LentBookViewDto;
import com.book.dto.LentRecordsViewDto;
import com.book.dto.MstBookViewDto;
import com.book.entity.LentManage;
import com.book.entity.LentRecords;
import com.book.entity.MstBook;
import com.book.entity.UserInfo;
import com.book.util.DateUtils;

/**
 * 図書管理 serviceクラス
 * @author AKITA
 *
 */
@Service
public class BookManageService {

	@Autowired
	private MstBookMapper mstBookMapper;

	@Autowired
	private LentManageMapper lentManageMapper;
	
	@Autowired
	private LentRecordsMapper lentRecordsMapper;
	
	@Autowired
	private UserInfoMapper userInfoMapper;

	@Autowired
	private CustomLentManageMapper customLentManageMapper;
	
	@Autowired
	private CustomLentRecordsMapper customLentRecordsMapper;

	public BookManageService(MstBookMapper mstBookMapper) {
		this.mstBookMapper = mstBookMapper;
	}

	/**
	 * 図書一覧表示
	 * @return
	 */
	public List<MstBookViewDto> findAll() {
		List<MstBook> list = mstBookMapper.findAll();
		List<MstBookViewDto> result = list.stream().map(book -> {
			MstBookViewDto dto = new MstBookViewDto();

			// 元のMstBookのプロパティをコピー
			dto.setBookId(book.getBookId());
			dto.setTitle(book.getTitle());
			dto.setPublisher(book.getPublisher());
			dto.setAuthor(book.getAuthor());
			dto.setLendingFlag(book.getLendingFlag());
			dto.setCreateDate(book.getCreateDate());
			dto.setUpdateDate(book.getUpdateDate());
			dto.setDeleteFlag(book.getDeleteFlag());

			// Enum変換
			if (book.getGenre1() != null) {
				GenreType genreType1 = GenreType.fromCode(book.getGenre1());
				dto.setGenreString1(genreType1 != null ? genreType1.getDisplayName() : null);
			}
			if (book.getGenre2() != null) {
				GenreType genreType2 = GenreType.fromCode(book.getGenre2());
				dto.setGenreString2(genreType2 != null ? genreType2.getDisplayName() : null);
			}
			if (book.getLendingFlag() != null) {
				AvailabilityType availabilityType = AvailabilityType.fromCode(book.getLendingFlag());
				dto.setLendString(availabilityType != null ? availabilityType.getDisplayName() : null);
			}

			return dto;
		}).collect(Collectors.toList());

		return result;

	}

	/**
	 * レンタル中図書一覧表示
	 * @param lentManage
	 * @return
	 */
	public List<LentBookViewDto> searchByMyLent() {
	    List<LentManage> lentList = customLentManageMapper.searchByUserIdAndFlags(getCurrentUserId());
	    List<MstBook> bookList = mstBookMapper.findAll();

	    List<LentBookViewDto> result = lentList.stream().map(lentItem -> {
	        // 各 lentItem に対応する MstBook を検索
	        MstBook correspondingBook = bookList.stream()
	                .filter(book -> book.getBookId().equals(lentItem.getBookId()))
	                .findFirst()
	                .orElse(null); // 一致するものがない場合は null を返す

	        LentBookViewDto dto = new LentBookViewDto();
	        if (correspondingBook != null) {
	            dto.setBookId(correspondingBook.getBookId());
	            dto.setTitle(correspondingBook.getTitle());
	            dto.setLendingFlag(correspondingBook.getLendingFlag());
	        }
	        

	        // LentManage のデータを設定
	        dto.setUserId(lentItem.getUserId());
	        dto.setExpectedReturndate(DateUtils.dateFormat(lentItem.getExpectedReturndate()));
	        dto.setLentdate(DateUtils.dateFormat(lentItem.getLentdate()));
	        dto.setDeleteFlag(lentItem.getDeleteFlag());

	        return dto;
	    }).collect(Collectors.toList());

	    return result;
	}


	/**
	 * 図書登録
	 * @param mstBook
	 */
	public void insert(MstBook mstBook) {
		Timestamp nowDate = DateUtils.getNowDate();

		mstBook.setBookId(getNextBookId());
		mstBook.setLendingFlag(0);
		mstBook.setCreateDate(nowDate);
		mstBook.setUpdateDate(nowDate);
		mstBook.setDeleteFlag(0);

		mstBookMapper.insert(mstBook);

	}

	/**
	 * 図書更新
	 * @param mstBook
	 */
	public void update(MstBook mstBook) {
		Timestamp nowDate = DateUtils.getNowDate();

		MstBook beforeMstBook = mstBookMapper.selectByPrimaryKey(mstBook.getBookId());
		mstBook.setCreateDate(beforeMstBook.getCreateDate());
		mstBook.setUpdateDate(nowDate);

		mstBookMapper.updateByPrimaryKeySelective(mstBook);
	}

	/**
	 * 図書検索
	 * @param mstBook
	 */
	public List<MstBookViewDto> search(MstBook mstBook) {
		List<MstBook> list = mstBookMapper.searchBook(mstBook);
		List<MstBookViewDto> result = list.stream().map(book -> {
			MstBookViewDto dto = new MstBookViewDto();

			// 元のMstBookのプロパティをコピー
			dto.setBookId(book.getBookId());
			dto.setTitle(book.getTitle());
			dto.setPublisher(book.getPublisher());
			dto.setAuthor(book.getAuthor());
			dto.setCreateDate(book.getCreateDate());
			dto.setUpdateDate(book.getUpdateDate());
			dto.setDeleteFlag(book.getDeleteFlag());

			// Enum変換
			if (book.getGenre1() != null) {
				GenreType genreType1 = GenreType.fromCode(book.getGenre1());
				dto.setGenreString1(genreType1 != null ? genreType1.getDisplayName() : null);
			}
			if (book.getGenre2() != null) {
				GenreType genreType2 = GenreType.fromCode(book.getGenre2());
				dto.setGenreString2(genreType2 != null ? genreType2.getDisplayName() : null);
			}
			if (book.getLendingFlag() != null) {
				AvailabilityType availabilityType = AvailabilityType.fromCode(book.getLendingFlag());
				dto.setLendString(availabilityType != null ? availabilityType.getDisplayName() : null);
			}

			return dto;
		}).collect(Collectors.toList());

		return result;

	}

	/**
	 * 図書貸出
	 * @param bookId
	 */
	public void lentBookInsert(Integer bookId) {
		LentManage lentManage = new LentManage();
		MstBook mstBook = new MstBook();

		Timestamp nowDate = DateUtils.getNowDate();
		Timestamp oneWeekLaterTimestamp = DateUtils.calculateOneWeekLaterWithEndTime(nowDate);

		lentManage.setLentId(getNextLentId());
		lentManage.setBookId(bookId);
		lentManage.setUserId(getCurrentUserId());
		lentManage.setLentdate(nowDate);
		lentManage.setExpectedReturndate(oneWeekLaterTimestamp);
		lentManage.setReturndate(DateUtils.initializeReturnDate());
		lentManage.setLentdate(nowDate);
		lentManage.setLentflag(1);
		lentManage.setCreateDate(nowDate);
		lentManage.setUpdateDate(nowDate);
		lentManage.setDeleteFlag(0);

		mstBook.setBookId(bookId);
		mstBook.setLendingFlag(1);
		mstBook.setUpdateDate(nowDate);

		lentManageMapper.insert(lentManage);
		mstBookMapper.updateByPrimaryKeySelective(mstBook);
		this.lentRecordsInsert(bookId, lentManage);

	}

	/**
	 * 図書返却
	 * @param bookId
	 */
	public void returnBook(Integer bookId) {
		LentManage lentManage = new LentManage();
		MstBook mstBook = new MstBook();

		Timestamp nowDate = DateUtils.getNowDate();

		lentManage.setBookId(bookId);
		lentManage.setReturndate(nowDate);
		lentManage.setLentflag(0);

		mstBook.setBookId(bookId);
		mstBook.setLendingFlag(0);
		mstBook.setUpdateDate(nowDate);

		customLentManageMapper.updateByBookId(lentManage);
		mstBookMapper.updateByPrimaryKeySelective(mstBook);
		this.updatelentRecords(bookId);
	}
	
	/**
	 * 貸出履歴一覧
	 * @return
	 */
	public List<LentRecordsViewDto>searchLentRecords(){
		List<LentRecords> lentRecords = customLentRecordsMapper.findAll();
		List<MstBook> bookList = mstBookMapper.findAll();
		List<UserInfo> userList = userInfoMapper.findAll();
		
		Map<Integer, MstBook> bookMap = bookList.stream().collect(Collectors.toMap(MstBook::getBookId, book -> book));
		Map<Integer, UserInfo> userMap = userList.stream().collect(Collectors.toMap(UserInfo::getUserId, user -> user));

		List<LentRecordsViewDto> lentRecordsViewDtoList = lentRecords.stream().map(record -> {
		    MstBook book = bookMap.get(record.getBookId());
		    UserInfo user = userMap.get(record.getUserId());

		    LentRecordsViewDto dto = new LentRecordsViewDto();
		    dto.setTitle(book.getTitle());
		    dto.setLendingFlag(book.getLendingFlag());
		    dto.setUserName(user.getUserName());
		    dto.setLentFlag(record.getLentflag());
		    dto.setLentdate(DateUtils.dateFormat(record.getLentdate()));
		    dto.setReturnDate(record.getReturndate() != null ? DateUtils.dateFormat(record.getReturndate()) : null);
		    dto.setExpectedReturndate(DateUtils.dateFormat(record.getExpectedReturndate()));
		    dto.setDeleteFlag(record.getDeleteFlag());
		    
		    // Enum変換
		    if (record.getLentflag() != null) {
		    	LentRecordsType lentRecordsType = LentRecordsType.fromCode(record.getLentflag());
				dto.setLentFlagString(lentRecordsType != null ? lentRecordsType.getDisplayName() : null);
			}

		    return dto;
		}).collect(Collectors.toList());
		
		return lentRecordsViewDtoList;
		
	}
	
	/**
	 * 貸出履歴登録
	 * @param bookId
	 */
	public void lentRecordsInsert(Integer bookId, LentManage lentManage) {
		Timestamp nowDate = DateUtils.getNowDate();
		Timestamp oneWeekLaterTimestamp = DateUtils.calculateOneWeekLaterWithEndTime(nowDate);
		
		LentRecords lentRecords = new LentRecords();
		lentRecords.setRecordsId(getNextLecordsId());
		lentRecords.setLentId(lentManage.getLentId());
		lentRecords.setBookId(bookId);
		lentRecords.setUserId(getCurrentUserId());
		lentRecords.setLentdate(nowDate);
		lentRecords.setExpectedReturndate(oneWeekLaterTimestamp);
		lentRecords.setReturndate(DateUtils.initializeReturnDate());
		lentRecords.setLentflag(1);
		lentRecords.setCreateDate(nowDate);
		lentRecords.setUpdateDate(nowDate);
		lentRecords.setDeleteFlag(0);
		
		lentRecordsMapper.insert(lentRecords);
	}
	
	/**
	 * 貸出履歴更新
	 * @param bookId
	 */
	public void updatelentRecords(Integer bookId) {
		LentRecords lentRecords = new LentRecords();
		
		Timestamp nowDate = DateUtils.getNowDate();
		
		lentRecords.setBookId(bookId);
		lentRecords.setReturndate(nowDate);
		lentRecords.setLentflag(0);
		lentRecords.setUpdateDate(nowDate);
		
		customLentRecordsMapper.updateLentRecords(lentRecords);
		System.out.println(lentRecords);
	}

	/**
	 * 主キー検索
	 * @param bookId
	 * @return
	 */
	public MstBook findById(Integer bookId) {
		return mstBookMapper.selectByPrimaryKey(bookId);
	}

	/**
	 * 図書情報削除
	 * @param bookId
	 */
	public void delete(Integer bookId) {
		mstBookMapper.logicalDeleteByPrimaryKey(bookId);
	}

	/**
	 * BookId連番採取
	 * @return getNextBookId
	 */
	public Integer getNextBookId() {
		Integer lastBookId = mstBookMapper.getLastBookId();
		if (lastBookId == null) {
			return 1; // テーブルが空の場合は1を返す
		}
		return lastBookId + 1; // 最後のIDに1を加算
	}

	/**
	 * lentId連番採取
	 * @return getNextlentId
	 */
	public Integer getNextLentId() {
		Integer lastLentId = customLentManageMapper.getLastLentId();
		if (lastLentId == null) {
			return 1; // テーブルが空の場合は1を返す
		}
		return lastLentId + 1; // 最後のIDに1を加算
	}
	
	/**
	 * recordsId連番採取
	 * @return getNextlentId
	 */
	public Integer getNextLecordsId() {
		Integer lastRecordsId = customLentRecordsMapper.getLastRecordsId();
		if (lastRecordsId == null) {
			return 1; // テーブルが空の場合は1を返す
		}
		return lastRecordsId + 1; // 最後のIDに1を加算
	}

	//ログインユーザのID取得
	public Integer getCurrentUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof LoginUserDetails) {
			LoginUserDetails userDetails = (LoginUserDetails) principal;
			return userDetails.getLoginUser().id(); // ユーザーIDを取得
		}

		return null; // ユーザーが認証されていない場合
	}

}