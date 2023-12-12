package com.book.service;

import org.springframework.stereotype.Service;

import com.book.dao.UserInfoMapper;
import com.book.entity.UserInfo;

@Service
public class UserLoginService {

    private final UserInfoMapper userInfoMapper;

    public UserLoginService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    public UserInfo authenticate(Integer userId, String password) {
        UserInfo user = userInfoMapper.selectByPrimaryKey(userId);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
