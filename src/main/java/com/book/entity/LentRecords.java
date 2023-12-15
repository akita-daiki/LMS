package com.book.entity;

import java.util.Date;

import lombok.Data;

@Data
public class LentRecords {

	private Integer recordsId;
	
	private Integer lentId;

	private Integer bookId;

	private Integer userId;

	private Date lentdate;

	private Date expectedReturndate;

	private Date returndate;

	private Integer lentflag;

	private Date createDate;

	private Date updateDate;

	private Integer deleteFlag;

}