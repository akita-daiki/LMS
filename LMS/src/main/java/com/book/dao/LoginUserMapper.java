package com.book.dao;

import com.book.entity.LoginUser;

public interface LoginUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loginuser
     *
     * @mbg.generated Wed Dec 06 11:03:32 JST 2023
     */
    int deleteByPrimaryKey(Integer userName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loginuser
     *
     * @mbg.generated Wed Dec 06 11:03:32 JST 2023
     */
    int insert(LoginUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loginuser
     *
     * @mbg.generated Wed Dec 06 11:03:32 JST 2023
     */
    int insertSelective(LoginUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loginuser
     *
     * @mbg.generated Wed Dec 06 11:03:32 JST 2023
     */
    LoginUser selectByPrimaryKey(Integer userName);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loginuser
     *
     * @mbg.generated Wed Dec 06 11:03:32 JST 2023
     */
    int updateByPrimaryKeySelective(LoginUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table loginuser
     *
     * @mbg.generated Wed Dec 06 11:03:32 JST 2023
     */
    int updateByPrimaryKey(LoginUser record);
}