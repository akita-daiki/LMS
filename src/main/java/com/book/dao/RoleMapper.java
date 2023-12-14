package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.Role;

/**
 * ロールテーブルマッパー
 * 
 * @author nakajima_yasuo
 */
@Mapper
public interface RoleMapper {

    /**
     * 全検索します.
     * 
     * @return ロール情報
     */
    List<Role> searchAll();
    
    /**
     * 検索します.
     * 
     * @param roleIds ロールIDs
     * @return 検索結果
     */
    List<Role> searchByIds(List<Integer> roleIds);
    
}