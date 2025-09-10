package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;
import com.example.mybatis.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    // 构造注入
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> listUsers() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> selectById(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    @Transactional
    public User addUser(User user) {
        userMapper.insert(user);
        return user; // 插入后 user.id 会自动回填
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        int rows = userMapper.update(user);
        if (rows == 0) {
            throw new RuntimeException("修改失败，用户ID不存在：" + user.getId());
        }
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        int rows = userMapper.deleteById(id);
        if (rows == 0) throw new RuntimeException("删除失败，用户ID不存在：" + id);
    }
}

