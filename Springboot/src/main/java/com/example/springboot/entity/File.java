package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("file")
@Data
public class File {
    @TableId(type = IdType.AUTO)
    //文件夹编号
    private Integer fileId;
    //所属类别编号
    private String category;
    //文件夹名称
    private String fileName;

    private boolean checked;

    private boolean displayed;

    private String myStyle;
}
