package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.UserMapper;
import com.example.mybatisplus.model.User;
import com.example.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ServiceImpl 已经帮你把 BaseMapper 注入好了（this.baseMapper）
 * 可直接使用：save/update/remove/getById/list/… 等通用方法
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<User> listUsers() {
        return userMapper.selectList(null);// MyBatis-Plus 自带方法
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserWithOrders(Long userId) {
        return this.baseMapper.selectUserWithOrders(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsersWithOrders() {
        return this.baseMapper.selectAllUsersWithOrders();
    }
}
