package com.book.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Book {
	
	private Integer book;
	
	private String title;
	
	private String publisher;
	
	private String author;
	
	private Date releaseDate;
	
	private Integer pages;
	
	private Boolean lendingFlag;
	
	private Boolean deleteFlag;
	
	private Date createDate;
	
	private Date updateDate;

}
