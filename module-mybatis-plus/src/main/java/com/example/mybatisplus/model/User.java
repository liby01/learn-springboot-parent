package com.example.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.List;

@Data
@TableName("t_user")
public class User {
    @TableId
    private Long id;
    private String name;

    /** 业务场景：为了多表返回把订单一并带回；该字段不是表字段，需要标注 exist=false */
    @TableField(exist = false)
    private List<Order> orders;
}