package com.book.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LentBookViewDto implements Serializable {

	private Integer bookId;

	private String title;

	private Integer lendingFlag;
	
	private Integer userId;
	
	private String lentdate;
	
	private String expectedReturndate;

	private Integer deleteFlag;

}
