package com.example.mysql.controller;
import com.example.mysql.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
@RestController
public class HelloController {
    private final UserRepo repo;

    public HelloController(UserRepo repo) { this.repo = repo; }
    @GetMapping("/users")
    public List<Map<String,Object>> users() { return repo.list(); }
}
