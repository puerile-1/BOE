package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PubProgram {
    private static final long serialVersionUID = 1L;

    @TableId(value = "pub_program_id", type = IdType.AUTO)
    private Integer pubProgramId;

    private String pubProgramImg;

}
