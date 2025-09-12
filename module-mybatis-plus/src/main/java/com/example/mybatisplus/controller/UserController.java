package com.example.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mybatisplus.model.User;
import com.example.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 演示：
 *  - 单表 CRUD：直接用 MyBatis-Plus 的通用 Service 方法
 *  - 多表查询：调用自定义 Service 方法（底层 XML 实现）
 */
@RestController
@RequestMapping("/users") // 统一的请求前缀
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/findAll")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    /* ---------- 单表：CRUD ---------- */

    @PostMapping("/add")
    public boolean create(@RequestBody User user) {
        return userService.save(user); // INSERT
    }

    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.updateById(user); // UPDATE
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return userService.removeById(id); // DELETE
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.getById(id); // SELECT BY ID
    }

    @GetMapping
    public List<User> list(@RequestParam(required = false) String name) {
        // 条件构造器：name 不为空时做 like 查询
        return userService.list(new LambdaQueryWrapper<User>()
                .like(name != null && !name.isEmpty(), User::getName, name));
    }

    /* ---------- 多表：用户 + 订单 ---------- */

    @GetMapping("/{id}/orders")
    public User getWithOrders(@PathVariable Long id) {
        return userService.getUserWithOrders(id);
    }

    @GetMapping("/with-orders")
    public List<User> listWithOrders() {
        return userService.listUsersWithOrders();
    }
}