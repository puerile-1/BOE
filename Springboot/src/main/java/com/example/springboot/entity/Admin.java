package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("admin")
@Data
public class Admin {
    @TableId(type = IdType.AUTO)
    private Integer adminId;

    private String adminName;

    private String tel;

    private String institutionsId;

    private String userState;

    private String role;

    private String email;

    private String pwd;

    private String trueName;
}
