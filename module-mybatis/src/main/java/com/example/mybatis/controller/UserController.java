package com.example.mybatis.controller;

import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.model.User;
import com.example.mybatis.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 标记为 REST 风格控制器（返回 JSON）
@RequestMapping("/users") // 统一的请求前缀
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    public UserController(UserService userService,UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping("/all")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/{id}")
    public List<User> listUsersById(@PathVariable("id") Long id) {
        return userService.selectById(id);
    }

    // 新增用户
    @PostMapping("/add")
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    // 修改用户
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id); // 确保路径里的 id 传给对象
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @GetMapping("/page")
    public List<User> list(@RequestParam(defaultValue = "1") int page,
                           @RequestParam(defaultValue = "5") int size,
                           @RequestParam(required = false) String q) {
        int offset = (page - 1) * size;
        return userMapper.selectPage(offset, size, q);
    }

    @GetMapping("/page/count")
    public int count(@RequestParam(required = false) String q) {
        return userMapper.countByKeyword(q);
    }
}
