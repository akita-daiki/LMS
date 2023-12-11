package com.book.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.book.constants.AvailabilityConstants.AvailabilityType;
import com.book.constants.BookConstants.GenreType;
import com.book.dao.MstBookMapper;
import com.book.dto.MstBookViewDto;
import com.book.entity.MstBook;
import com.book.util.DateUtils;

@Service
public class BookManageService {

	private MstBookMapper mstBookMapper;

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
	 * 連番採取
	 * @return getNextBookId
	 */
	public Integer getNextBookId() {
		Integer lastBookId = mstBookMapper.getLastBookId();
		if (lastBookId == null) {
			return 1; // テーブルが空の場合は1を返す
		}
		return lastBookId + 1; // 最後のIDに1を加算
	}

}
