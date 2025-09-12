package com.example.admin.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.admin.user.SysUser;
import com.example.admin.user.SysUserMapper;
import com.example.admin.user.SysUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public SysUserServiceImpl(SysUserMapper mapper, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SysUser findByUsername(String username) {
        return mapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(String username, String rawPassword, String roles, boolean enabled) {
        SysUser u = new SysUser();
        u.setUsername(username);
        u.setPassword(passwordEncoder.encode(rawPassword)); // BCrypt
        u.setRoles(roles);
        u.setEnabled(enabled);
        mapper.insert(u);
        return u.getId();
    }
}
