package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.User;

import java.util.List;

/**
 * 继承 IService 拥有通用 CRUD（save/update/remove/get 等）
 * 另外扩展多表查询能力
 */
public interface UserService extends IService<User> {

    List<User> listUsers();

    /** 多表：查某个用户及其订单 */
    User getUserWithOrders(Long userId);

    /** 多表：查所有用户及其订单 */
    List<User> listUsersWithOrders();
}
