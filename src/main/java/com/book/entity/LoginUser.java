package com.book.entity;

import java.util.List;

/**
 * ログインユーザー
 * 
 * @author doublecrop
 */
public record LoginUser(Integer id, String email, String name, String password, List<String> roleList) {}
