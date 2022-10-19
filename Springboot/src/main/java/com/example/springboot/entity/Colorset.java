package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("colorset")
@Data
public class Colorset {
    @TableId(type = IdType.AUTO)
    //颜色编号
    private int id;
    //颜色名称
    private String name;
    //RGB值
    private int R;
    private int G;
    private int B;
}
