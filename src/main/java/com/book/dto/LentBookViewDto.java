package com.book.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LentBookViewDto implements Serializable {

	private Integer bookId;

	private String title;

	private Integer lendingFlag;
	
	private Integer userId;
	
	private Date lentdate;
	
	private Date expectedReturndate;

	private Integer deleteFlag;

}
