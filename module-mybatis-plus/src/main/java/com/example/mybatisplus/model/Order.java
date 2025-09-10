package com.example.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/** 对应表：t_order(id, user_id, amount, created_at) */
@Data
@TableName("t_order")
public class Order {
    @TableId
    private Long id;
    private Long userId;
    private BigDecimal amount;
    private LocalDateTime createdAt;
}