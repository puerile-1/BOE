package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("user")
@Data
public class User {
    @TableId(type = IdType.AUTO)
    //用户编号
    private int id;
    //用户名
    private String name;
    //密码
    private String pwd;
    //所属机构编号
    private int institutionsId;
    //用户状态编号
    private int userStateId;
    //真实姓名
    private String trueNAme;
    //联系电话
    private int phoneNumber;
    //电子邮箱
    private String email;
    //更新时间
    private Date update;
}
