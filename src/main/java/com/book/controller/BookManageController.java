package com.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.book.entity.MstBook;
import com.book.service.BookManageService;

/**
 * 図書管理　controller
 * @author AKITA
 *
 */
@Controller
public class BookManageController {

	private  BookManageService bookManageService;
	
	public BookManageController(BookManageService bookManageService) {
		this.bookManageService = bookManageService;
	}
	
	@GetMapping(value = "/book/list")
	public String displayList(Model model) 
	{
		List<MstBook> bookList = bookManageService.findAll();
		model.addAttribute("booklist",bookList);
		
		return "/book/list";
	}
	
	
	
}
