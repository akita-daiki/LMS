package com.book.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 書籍情報登録　リクエストデータ
 * @author AKITA
 *
 */
@Data
public class BookAddRequestDto implements Serializable	{
	
	/**
	 * タイトル
	 */
	@NotEmpty(message = "タイトルを入力してください")
	@Size(max = 100)
	private String reqTitle;
	
	/**
	 * 出版社
	 */
	@NotEmpty(message = "出版社を入力してください")
	@Size(max = 50)
	private String reqPublisher;
	
	/**
	 * 著者
	 */
	@Size(max = 50)
	private String reqAuthor;
	
	/**
	 * ジャンル1
	 */
	@NotEmpty(message = "ジャンルを選択してください")
	private Integer genre1;
	
	/**
	 * ジャンル2
	 */
	private Integer genre2;
	

}
