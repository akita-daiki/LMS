package com.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.book.entity.UserInfo;
import com.book.service.UserLoginService;

/**
 * ユーザー情報 Controller
 */
@Controller
public class UserLoginController {

    private final UserLoginService userLoginService;

    public UserLoginController(UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") Integer userId,
                        @RequestParam("password") String password,
                        Model model) {
    	UserInfo user = userLoginService.authenticate(userId, password);
        if (user != null) {
            // ログイン成功
            return "redirect:/book/list";
        } else {
            // ログイン失敗
            model.addAttribute("error", "ユーザー名またはパスワードが正しくありません。");
            return "login";
        }
    }
}