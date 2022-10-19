package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("groups")
@Data
public class Group {
    @TableId(type = IdType.AUTO)
    //分组编号
    private Integer groupId;
    //分组名称
    private String groupName;
    //所属机构编号
    private String institutionsId;
    //设备数量
    private Integer number;
    //分组描述
    private String describes;
}
