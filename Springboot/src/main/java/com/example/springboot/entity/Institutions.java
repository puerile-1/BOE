package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("institutions")
@Data
public class Institutions {
    @TableId(type = IdType.AUTO)
    //机构编号
    private int id;
    //机构名称
    private String name;
}
