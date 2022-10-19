package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("material")
@Data
public class Material {
    @TableId(type = IdType.AUTO)
    //素材编号
    private int materialId;
    //素材名称
    private String materialName;
    //素材所属文件夹编号
    private String fileName;
    //素材存储地址
    private String data;

    private boolean checked;

    private boolean displayed;

    private String myStyle;

}
