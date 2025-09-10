package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 这里可以什么都不写，直接用 BaseMapper 提供的增删改查

    //也可以写自定义 SQL
    List<User> selectByNameLike(String name);

    /** 自定义：按用户ID查询用户及其订单（多表 join），对应 XML 中的 id */
    User selectUserWithOrders(@Param("userId") Long userId);

    /** 自定义：查询所有用户及其订单（演示集合映射） */
    List<User> selectAllUsersWithOrders();
}