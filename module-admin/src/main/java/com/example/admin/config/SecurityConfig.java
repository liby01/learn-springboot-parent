package com.example.admin.config;

import com.example.admin.auth.JwtAuthFilter;
import com.example.admin.auth.JwtUtil;
import com.example.admin.user.SysUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 配置类
 * 配置安全相关设置，包括密码加密、请求授权、认证管理等
 */

@Configuration
public class SecurityConfig {
    /**
     * 配置密码编码器
     * 使用BCrypt强哈希算法进行密码加密
     * @return BCryptPasswordEncoder实例
     */

    @Bean
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder(); 
    }

    /**
     * 配置安全过滤器链
     * 设置URL访问权限、CSRF、CORS等安全配置
     * @param http HttpSecurity对象
     * @param jwtUtil JWT工具类
     * @param userService 用户服务
     * @return 配置好的SecurityFilterChain
     * @throws Exception 配置异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil, SysUserService userService) throws Exception {
        http.csrf(csrf -> csrf.disable())  // 禁用CSRF保护（因为使用JWT）
                .cors(Customizer.withDefaults())  // 启用默认CORS配置
                .authorizeHttpRequests(reg -> reg
                        // 允许未认证访问的URL
                        .antMatchers(HttpMethod.POST, "/auth/login", "/auth/register").permitAll()
                        // 其他所有请求都需要认证
                        .anyRequest().authenticated()
                )
                // 添加JWT认证过滤器
                .addFilterBefore(new JwtAuthFilter(jwtUtil, userService), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 配置认证管理器
     * @param cfg 认证配置
     * @return AuthenticationManager实例
     * @throws Exception 配置异常
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
        return cfg.getAuthenticationManager();
    }
}
