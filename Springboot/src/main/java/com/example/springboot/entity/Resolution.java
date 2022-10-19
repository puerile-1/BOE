package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("resolution")
@Data
public class Resolution {
    @TableId(type = IdType.AUTO)
    //分辨率编号
    private int id;
    //分辨率
    private String name;
}
