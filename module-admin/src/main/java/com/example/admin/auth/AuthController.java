package com.example.admin.auth;

import com.example.admin.user.SysUser;
import com.example.admin.user.SysUserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 * 处理用户登录、注册和认证相关的HTTP请求
 */

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final SysUserService userService;  // 用户服务
    private final PasswordEncoder encoder;     // 密码编码器，用于密码加密和验证
    private final JwtUtil jwt;                 // JWT工具类，用于生成和验证token

    public AuthController(SysUserService userService, PasswordEncoder encoder, JwtUtil jwt) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    /**
     * 用户登录接口
     * @param req 登录请求体，包含用户名和密码
     * @return 登录成功返回JWT token和用户信息，失败返回错误信息
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq req) {
        // 根据用户名查询用户
        SysUser u = userService.findByUsername(req.getUsername());
        
        // 验证用户是否存在和是否启用
        if (u == null || !Boolean.TRUE.equals(u.getEnabled())) {
            return ResponseEntity.status(401).body("User not found or disabled");
        }
        
        // 验证密码
        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            return ResponseEntity.status(401).body("Bad credentials");
        }
        
        // 生成JWT token
        String token = jwt.generate(u.getUsername(), u.getRoles());
        return ResponseEntity.ok(new TokenResp(token, u.getUsername(), u.getRoles()));
    }

    /**
     * 用户注册接口
     * 注意：生产环境建议关闭此接口或添加权限控制
     * @param req 注册请求体，包含用户名和密码
     * @return 注册成功返回用户ID，失败返回错误信息
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq req) {
        // 检查用户名是否已存在
        if (userService.findByUsername(req.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username exists");
        }
        
        // 创建新用户，默认赋予ROLE_ADMIN角色
        Long id = userService.createUser(req.getUsername(), req.getPassword(), "ROLE_ADMIN", true);
        return ResponseEntity.ok(id);
    }

    /**
     * 获取当前用户信息
     * 用于验证token是否有效
     * @param authz 请求头中的Authorization字段，格式为"Bearer <token>"
     * @return 当前用户的基本信息
     */
    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader("Authorization") String authz) {
        // 返回认证信息，实际项目中应该解析token并返回用户详细信息
        return ResponseEntity.ok("You are: " + authz);
    }

    /**
     * 登录请求体
     */
    @Data 
    public static class LoginReq { 
        private String username;  // 用户名
        private String password;  // 密码
    }
    
    /**
     * 注册请求体
     */
    @Data 
    public static class RegisterReq { 
        private String username;  // 用户名
        private String password;  // 密码
    }
    
    /**
     * Token响应体
     */
    @Data 
    public static class TokenResp {
        private final String token;     // JWT token
        private final String username;  // 用户名
        private final String roles;     // 用户角色，多个角色用逗号分隔
    }
}
