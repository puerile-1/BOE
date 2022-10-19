package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("announcement")
@Data
public class Announcement {
    //公告编号
    @TableId(type = IdType.AUTO)
    private int id;
    //公告内容
    private String content;
    //字体颜色编号
    private int color;
    //字体大小
    private int size;
    //背景颜色编号
    private int bgColor;
    //背景高度
    private int bgSize;
    //公告位置，1表示上，0表示中，-1表示下
    private int locations;
    //公告播放速度
    private int speed;
    //公告播放模式
    private int model;
    //开始时间
    private Date startDate;
    //结束时间
    private Date endDate;
    //用户编号
    private int userId;
    //设备编号
    private int equipmentId;
    //公告状态
    private int state;
}
