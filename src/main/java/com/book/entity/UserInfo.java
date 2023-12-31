package com.book.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.USER_ID
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private Integer userId;
    
    private String loginId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.PASSWORD
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.USER_NAME
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.DEPARTMENT
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private Integer department;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.CREATE_DATE
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.UPDATE_DATE
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.DELETE_FLAG
     *
     * @mbg.generated Tue Dec 12 15:09:54 JST 2023
     */
    private Integer deleteFlag;
}