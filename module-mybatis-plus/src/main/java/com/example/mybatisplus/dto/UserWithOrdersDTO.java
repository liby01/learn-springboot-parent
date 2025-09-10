package com.example.mybatisplus.dto;

import com.example.mybatisplus.model.Order;
import lombok.Data;

import java.util.List;

@Data
public class UserWithOrdersDTO {
    private Long id;
    private String name;
    private List<Order> orders;
}
