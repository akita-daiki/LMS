package com.book.dao;

import org.apache.ibatis.annotations.Mapper;

import com.book.entity.MstBook;

@Mapper
public interface MstBookMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_book
     *
     * @mbg.generated Tue Dec 05 16:43:51 JST 2023
     */
    int deleteByPrimaryKey(Integer bookId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_book
     *
     * @mbg.generated Tue Dec 05 16:43:51 JST 2023
     */
    int insert(MstBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_book
     *
     * @mbg.generated Tue Dec 05 16:43:51 JST 2023
     */
    int insertSelective(MstBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_book
     *
     * @mbg.generated Tue Dec 05 16:43:51 JST 2023
     */
    MstBook selectByPrimaryKey(Integer bookId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_book
     *
     * @mbg.generated Tue Dec 05 16:43:51 JST 2023
     */
    int updateByPrimaryKeySelective(MstBook record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mst_book
     *
     * @mbg.generated Tue Dec 05 16:43:51 JST 2023
     */
    int updateByPrimaryKey(MstBook record);
}