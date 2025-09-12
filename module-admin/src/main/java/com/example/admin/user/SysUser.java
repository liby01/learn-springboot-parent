package com.example.admin.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user")
public class SysUser {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String username;
    private String password; // 存BCrypt后的hash
    private String roles;    // 例: "ROLE_ADMIN,ROLE_USER"
    private Boolean enabled;
}
