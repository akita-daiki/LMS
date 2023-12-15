package com.book.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class LentRecordsViewDto implements Serializable {

	private String title;

	private Integer lendingFlag;
	
	private String userName;
	
	private Integer lentFlag;
	
	private String lentFlagString;
	
	private String lentdate;
	
	private String returnDate;
	
	private String expectedReturndate;

	private Integer deleteFlag;

}
