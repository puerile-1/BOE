package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("category")
@Data
public class Category {
    @TableId(type = IdType.AUTO)
    //类别编号
    private int id;
    //类别名称
    private String name;
}
