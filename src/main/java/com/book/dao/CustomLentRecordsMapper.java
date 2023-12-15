package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.LentRecords;

/**
 * 貸出履歴カスタムマッパー
 * @author AKITA
 *
 */
@Mapper
public interface CustomLentRecordsMapper {

	/**
	 * 連番採取
	 */
	Integer getLastRecordsId();
	
	/**
	 * 返却時履歴更新
	 */
	int updateLentRecords(LentRecords record);
	
	/**
	 * 全件検索
	 * @return
	 */
	List<LentRecords> findAll();

}