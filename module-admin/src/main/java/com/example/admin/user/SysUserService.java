package com.example.admin.user;

public interface SysUserService {

    SysUser findByUsername(String username);

    Long createUser(String username, String rawPassword, String roles, boolean enabled);
}
