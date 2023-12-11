package com.book.dto;

import com.book.entity.MstBook;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class MstBookViewDto extends MstBook {
	
	private static final long serialVersionUID = 1L;
	
	public String genreString1;
	
	public String genreString2;
	
	public String lendString;

}
