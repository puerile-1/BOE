package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("userstate")
@Data
public class UserState {
    @TableId(type = IdType.AUTO)
    //用户状态编号,1为已启用，2为已停用
    private int id;
    //用户状态名称
    private String name;
}
