package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("planstate")
@Data
public class PlanState {
    @TableId(type = IdType.AUTO)
    //计划状态编号
    private int id;
    //计划状态名称
    private String name;
}
