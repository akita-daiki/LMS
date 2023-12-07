package com.book.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.book.dao.MstBookMapper;
import com.book.entity.MstBook;

@Service
public class BookManageService {

	private MstBookMapper mstBookMapper;
	
	public BookManageService(MstBookMapper mstBookMapper) {
		this.mstBookMapper = mstBookMapper;
	}
	
	public List<MstBook> findAll(){
		return mstBookMapper.findAll();
	}
	
//	public List<MstBook> selectByPrimaryKey(){
//		return mstBookMapper.selectByPrimaryKey(bookId);
//	}
	
}
