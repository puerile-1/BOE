package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("program")
@Data
public class Program {
    @TableId(type = IdType.AUTO)
    //节目编号
    private Integer id;
    //节目名称
    private String name;
    //缩略图存储地址
    private String over;
    //分辨率
    private String resolutionId;
    //节目时长
    private String time;
    //节目大小
    private String size;
    //节目状态，0表示未使用，1表示已使用
    private int state;
    //用户名
    private String userId;
    //更新时间
    private Date updateTime;
    //素材1
    private int material1;
    //素材2
    private int material2;
    //素材3
    private int material3;
    //素材4
    private int material4;
}
