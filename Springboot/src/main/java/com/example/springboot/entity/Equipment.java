package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("equipment")
@Data
public class Equipment {
    @TableId(type = IdType.AUTO)
    //设备编号
    private Integer equipmentId;
    //设备名称
    private String equipmentName;
    //设备状态
    private String state;
    //所属机构
    private String institutionsId;
    //所属分组
    private Integer groupId;
    //MAC地址
    private String address;
    //分辨率编号
    private String resolutionId;
    //当前计划编号
    private String currentPlan;
    //系统升级
    private String system_update;
}
