package com.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.UserInfo;

@Mapper
public interface UserInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    UserInfo selectByPrimaryKey(Integer userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    int updateByPrimaryKey(UserInfo record);
    
    UserInfo searchByLoginId(String loginId);
    
    List<UserInfo> findAll();
}