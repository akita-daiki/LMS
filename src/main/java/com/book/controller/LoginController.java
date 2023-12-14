package com.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ログインコントローラー
 * 
 * @author doublecrop
 */
@Controller
public class LoginController {

    /**
     * 初期表示をします.
     * 
     * @return index
     */
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    /**
     * ログイン画面を表示します.
     * 
     * @return login
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 一般画面を表示します.
     * 
     * @return general
     */
    @GetMapping("/general")
    public String general() {
        return "/general";
    }

    /**
     * 管理画面を表示します.
     * 
     * @return admin
     */
    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }
}
