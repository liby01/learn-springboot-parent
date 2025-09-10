package com.example.mybatis.controller;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class UserController {
    private final UserMapper mapper;
    public UserController(UserMapper mapper){this.mapper=mapper;}
    @GetMapping("/users")
    public List<User> users(){return mapper.selectAll();}
}
