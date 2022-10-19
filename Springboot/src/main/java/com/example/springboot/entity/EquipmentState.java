package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("equipmentstate")
@Data
public class EquipmentState {
    @TableId(type = IdType.AUTO)
    //设备状态编号
    private int id;
    //设备状态
    private String name;
}
