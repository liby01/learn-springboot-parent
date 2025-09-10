package com.example.mybatis.mapper;

import com.example.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll();

    List<User> selectById(Long id);

    int insert(User user);

    int update(User user);

    int deleteById(Long id);
}
