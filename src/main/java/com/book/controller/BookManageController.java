package com.book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.book.constants.BookConstants.GenreType;
import com.book.dto.MstBookViewDto;
import com.book.entity.MstBook;
import com.book.service.BookManageService;

/**
 * 図書管理　controller
 * @author AKITA
 *
 */
@Controller
public class BookManageController {

	private BookManageService bookManageService;

	public BookManageController(BookManageService bookManageService) {
		this.bookManageService = bookManageService;
	}

	/**
	 * 図書一覧表示
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/book/list")
	public String displayList(Model model) {
		List<MstBookViewDto> bookList = bookManageService.findAll();
		model.addAttribute("booklist", bookList);

		return "book/list";
	}

	/**
	 * 図書新規登録画面を表示
	 * @param model
	 * @return
	 */
	@GetMapping("/book/add")
	private String displayAdd(Model model) {
		model.addAttribute("mstBook", new MstBook());
	    model.addAttribute("genres", GenreType.values()); 
	    
		
		return "book/add";
	}
	
	/**
	 * 図書情報登録
	 * @param mstBook
	 * @param result
	 * @param model
	 * @return
	 */
	@PostMapping("/book/create")
	 public String create(@Validated @ModelAttribute MstBook mstBook,  
			 BindingResult result, Model model) {
		if (result.hasErrors()) {
	        List<String> errorList = new ArrayList<>();
	        for (ObjectError error : result.getAllErrors()) {
	            errorList.add(error.getDefaultMessage());
	        }
	        model.addAttribute("validationError", errorList);
	        model.addAttribute("mstBook", mstBook); // エラー情報とともにmstBookも再度セット
	        model.addAttribute("genres", GenreType.values()); // genresも再度セット
	        return "book/add";
	    }
		
		// 図書情報の登録
		bookManageService.insert(mstBook);
		return "redirect:/book/list";
		
	}
}
