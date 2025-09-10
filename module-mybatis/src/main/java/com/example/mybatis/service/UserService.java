package com.example.mybatis.service;


import com.example.mybatis.model.User;

import java.util.List;

public interface UserService {
    List<User> listUsers();

    List<User> selectById(Long id);

    User addUser(User user);

    User updateUser(User user);

    void deleteById(Long id);
}
