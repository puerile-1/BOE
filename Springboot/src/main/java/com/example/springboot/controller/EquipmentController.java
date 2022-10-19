package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Group;
import com.example.springboot.entity.Login;
import com.example.springboot.entity.Equipment;
import com.example.springboot.mapper.EquipmentMapper;
import com.example.springboot.mapper.GroupMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Resource
    EquipmentMapper equipmentMapper;
    @Resource
    GroupMapper groupMapper;
    //新增
    @PostMapping
    public Result save(@RequestBody Equipment equipment){
        equipmentMapper.insert(equipment);
        if (String.valueOf(equipment.getGroupId()).equals("0") || String.valueOf(equipment.getGroupId()).equals("null")) {
            return Result.success(200,"增加成功",equipment);
        } else {    //新增的设备设置了分组，该分组数量 + 1
            QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
            queryWrapper.select("*").eq("group_id",equipment.getGroupId());
            Group group = groupMapper.selectOne(queryWrapper);
            group.setNumber(group.getNumber() + 1);
            groupMapper.updateById(group);
            return Result.success(200,"增加成功",equipment);
        }
    }
    //更新
    @PutMapping
    public Result update(@RequestBody Equipment equipment){
        //查询当前设备原分组
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("group_id").eq("equipment_id",equipment.getEquipmentId());
        Equipment thisEquipment = equipmentMapper.selectOne(queryWrapper);
        //原分组数量-1
        QueryWrapper<Group> groupQueryWrapper = new QueryWrapper<>();
        groupQueryWrapper.select("*").eq("group_id",thisEquipment.getGroupId());
        Group group = groupMapper.selectOne(groupQueryWrapper);
        group.setNumber(group.getNumber() - 1);
        groupMapper.updateById(group);
        //现分组数量+1
        QueryWrapper<Group> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("group_id",equipment.getGroupId());
        group = groupMapper.selectOne(wrapper);
        group.setNumber(group.getNumber() + 1);
        groupMapper.updateById(group);
        equipmentMapper.updateById(equipment);
        return Result.success(200,"更新成功",equipment);
    }
    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        //查询当前设备分组
        QueryWrapper<Equipment> equipmentQueryWrapper = new QueryWrapper<>();
        equipmentQueryWrapper.select("group_id").eq("equipment_id",id);
        Equipment equipment = equipmentMapper.selectOne(equipmentQueryWrapper);
        //将当前设备所在分组数量-1
        QueryWrapper<Group> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("group_id",equipment.getGroupId());
        Group group = groupMapper.selectOne(queryWrapper);
        group.setNumber(group.getNumber() - 1);
        groupMapper.updateById(group);
        //删除设备
        equipmentMapper.deleteById(id);
        return Result.success(200,"删除成功",id);
    }
    //查询
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String equipmentName,
                              @RequestParam (defaultValue = "") String institutions,
                              @RequestParam (defaultValue = "") Integer groupId,
                              @RequestParam (defaultValue = "") String address,
                              @RequestParam (defaultValue = "") String resolution,
                              @RequestParam (defaultValue = "") String equipmentState,
                              @RequestParam (defaultValue = "") String system_update,
                              @RequestParam (defaultValue = "") String currentPlan){
        LambdaQueryWrapper<Equipment> wrapper =  Wrappers.<Equipment>lambdaQuery();
        if (StrUtil.isNotBlank(equipmentName) ||
                StrUtil.isNotBlank(institutions) ||
                !String.valueOf(groupId).equals(null) ||
                StrUtil.isNotBlank(address) ||
                StrUtil.isNotBlank(resolution) ||
                StrUtil.isNotBlank(equipmentState) ||
                StrUtil.isNotBlank(system_update) ||
                StrUtil.isNotBlank(currentPlan)){
            wrapper.like(Equipment::getEquipmentName,equipmentName)
                    .like(Equipment::getInstitutionsId,institutions)
                    .like(Equipment::getGroupId,groupId)
                    .like(Equipment::getAddress,address)
                    .like(Equipment::getResolutionId,resolution)
                    .like(Equipment::getState,equipmentState)
                    .like(Equipment::getSystem_update,system_update)
                    .like(Equipment::getCurrentPlan,currentPlan);
        }
        Page<Equipment> equipmentPage = equipmentMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(equipmentPage);
    }

}
