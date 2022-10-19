package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("plan")
@Data
public class Plan {
    @TableId(type = IdType.AUTO)
    //计划编号
    private int id;
    //缩略图存储地址
    private String  over;
    //计划名称
    private String name;
    //计划状态编号,1为待发布,2为发布中,3为已结束,4为发布成功,5为发布失败
    private int stateId;
    //计划播放模式
    private int model;
    //计划播放时间
    private String date;
    //用户编号
    private String userId;
    //审核人编号
    private String reviewerId;
    //更新时间
    private Date updateTime;
    //是否多屏同步，0表示false，1表示true
    private boolean synchronous;
    //节目编号
    private int programId;
    //设备编号
    private int equipmentId;
}
