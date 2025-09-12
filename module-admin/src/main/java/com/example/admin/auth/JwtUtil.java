package com.example.admin.auth;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JWT工具类，用于生成和解析JSON Web Tokens
 * 使用HMAC-SHA算法进行签名验证
 */

@Component
public class JwtUtil {
    @Value("${app.jwt.secret}")
    private String secret;  // JWT密钥，从配置文件中读取

    @Value("${app.jwt.expireMinutes}")
    private long expireMinutes;  // token过期时间（分钟），从配置文件中读取

    /**
     * 生成JWT Token
     * @param username 用户名
     * @param roles 用户角色，多个角色使用逗号分隔
     * @return 生成的JWT字符串
     */
    public String generate(String username, String roles) {
        Date now = new Date();
        // 计算token过期时间
        Date exp = new Date(now.getTime() + expireMinutes * 60_000);
        
        // 构建JWT Token
        return Jwts.builder()
                .setSubject(username)  // 设置主题（通常是用户名）
                .claim("roles", roles)  // 添加自定义声明：用户角色
                .setIssuedAt(now)  // 设置签发时间
                .setExpiration(exp)  // 设置过期时间
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))  // 使用密钥签名
                .compact();  // 生成最终的token字符串
    }

    /**
     * 解析JWT Token
     * @param token JWT字符串
     * @return 解析后的JWT对象，包含头部、负载和签名信息
     * @throws ExpiredJwtException token已过期
     * @throws UnsupportedJwtException token格式错误
     * @throws MalformedJwtException token结构错误
     * @throws SignatureException 签名验证失败
     * @throws IllegalArgumentException token为空或null
     */
    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))  // 设置签名密钥
                .build()
                .parseClaimsJws(token);  // 解析token
    }
}
