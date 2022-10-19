package com.example.springboot.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Plan;
import com.example.springboot.entity.Program;
import com.example.springboot.mapper.PlanMapper;
import com.example.springboot.mapper.ProgramMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/plan")
public class PlanController {
    @Resource
    PlanMapper planMapper;
    @Resource
    ProgramMapper programMapper;
    //添加计划
    @PostMapping
    public Result save(@RequestBody Plan plan) {
        //判断是否选择设备号........
        if(String.valueOf(plan.getEquipmentId()).equals("0") || String.valueOf(plan.getEquipmentId()).equals(null)) {
            plan.setStateId(2);     //设置计划状态为发布中
        } else {
            plan.setStateId(1);     //设置计划状态为待发布
        }
        //设置使用的节目状态为使用中
        QueryWrapper<Program> programQueryWrapper = new QueryWrapper<>();
        programQueryWrapper.select("*").eq("id",plan.getProgramId());
        Program program = programMapper.selectOne(programQueryWrapper);
        program.setState(1);
        //更新节目状态
        programMapper.updateById(program);
        //增加计划
        if(plan.getModel() == 1) {
            plan.setDate("持续播放");
        }
        plan.setUpdateTime(DateTime.now());
        planMapper.insert(plan);
        return Result.success(200,"增加成功",plan);
    }
    //修改计划
    @PutMapping
    public Result update(@RequestBody Plan plan) {
        //判断是否选择设备号
        if(String.valueOf(plan.getEquipmentId()).equals("0") || String.valueOf(plan.getEquipmentId()).equals(null)) {
            plan.setStateId(2);     //设置计划状态为发布中
            return Result.error("该计划发布中，不能修改");
        } else {
            plan.setStateId(1);     //设置计划状态为待发布
            plan.setUpdateTime(DateTime.now());
            planMapper.updateById(plan);
            return Result.success(200,"修改成功",plan);
        }
    }
    //删除计划
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        //获取当前要删除的计划使用的节目
        QueryWrapper<Plan> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("id",id);
        Plan plan = planMapper.selectOne(queryWrapper);
        planMapper.deleteById(id);
        //查找计划列表中是否还有使用该节目的计划
        QueryWrapper<Plan> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("program_id",plan.getProgramId());
        Plan plan1 = planMapper.selectOne(wrapper);
        if (plan1.equals(null) || plan1 == null) {    //没有其他计划使用该节目，修改该节目状态
            QueryWrapper<Program> programQueryWrapper = new QueryWrapper<>();
            programQueryWrapper.select("*").eq("id",plan.getProgramId());
            Program program = programMapper.selectOne(programQueryWrapper);
            program.setState(0);    //设置节目状态为未使用
            programMapper.updateById(program);
        }
        return Result.success(200,"删除成功",id);
    }

    //查询计划
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String name,
                              @RequestParam (defaultValue = "") String state) {
        LambdaQueryWrapper<Plan> wrapper = Wrappers.<Plan>lambdaQuery();
        if(StrUtil.isNotBlank(name) || StrUtil.isNotBlank(state)) {
            wrapper.like(Plan::getName,name).like(Plan::getStateId,state);
        }
        Page<Plan> planPage = planMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(planPage);
    }
}
