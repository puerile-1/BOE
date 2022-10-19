package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Equipment;
import com.example.springboot.entity.Group;

import com.example.springboot.mapper.EquipmentMapper;
import com.example.springboot.mapper.GroupMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/group")
public class GroupController {
    @Resource
    GroupMapper GroupMapper;
    @Resource
    EquipmentMapper equipmentMapper;
    //新增
    @PostMapping
    public Result save(@RequestBody Group group){
        GroupMapper.insert(group);
        return Result.success(200,"增加成功",group);
    }
    //更新
    @PutMapping
    public Result update(@RequestBody Group group){
        GroupMapper.updateById(group);
        return Result.success(200,"更新成功",group);
    }
    //删除分组
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        GroupMapper.deleteById(id);
        return Result.success(200,"删除成功",id);
    }
    //查询
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search,
                              @RequestParam (defaultValue = "") String institutions){
        LambdaQueryWrapper<Group> wrapper =  Wrappers.<Group>lambdaQuery();
        if (StrUtil.isNotBlank(search) || StrUtil.isNotBlank(institutions)){
            wrapper.like(Group::getGroupName,search).like(Group::getGroupId,institutions);
        }
        Page<Group> groupPage = GroupMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(groupPage);
    }

}
