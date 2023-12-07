package com.book.service;

import org.springframework.stereotype.Service;

import com.book.dao.LoginUserMapper;
import com.book.entity.LoginUser;

@Service
public class UserLoginService {

    private final LoginUserMapper loginUserMapper;

    public UserLoginService(LoginUserMapper loginUserMapper) {
        this.loginUserMapper = loginUserMapper;
    }

    public LoginUser authenticate(Integer userName, String password) {
        LoginUser user = loginUserMapper.selectByPrimaryKey(userName);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
