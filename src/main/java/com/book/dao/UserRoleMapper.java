package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.UserRole;

/**
 * ユーザーテーブルマッパー
 * 
 * @author nakajima_yasuo
 */
@Mapper
public interface UserRoleMapper {

    /**
     * 検索します.
     * 
     * @param userId ユーザーID
     * @return 検索結果
     */
    List<UserRole> searchByUserId(Integer userId);
    
    /**
     * 登録します.
     * 
     * @param userRoles 登録用リクエストデータ
     */
    void bulkInsert(List<UserRole> userRoles);
    
}