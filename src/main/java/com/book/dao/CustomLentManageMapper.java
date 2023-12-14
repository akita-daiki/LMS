package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.LentManage;

/**
 * ロールテーブルマッパー
 * 
 * @author nakajima_yasuo
 */
@Mapper
public interface CustomLentManageMapper {

	/**
	 * 連番採取
	 */
	Integer getLastLentId();
    
    /**
     * bookIdで更新
     */
	int updateByBookId(LentManage record);
	
	/**
	 * レンタル中図書検索
	 * @param record
	 * @return
	 */
	List<LentManage> searchByUserIdAndFlags(Integer userId);

}