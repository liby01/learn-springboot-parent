package com.example.mybatis.mapper;
import com.example.mybatis.model.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
@Mapper
public interface UserMapper {
    List<User> selectAll();
}
