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
@RequestMapping("/program")
public class ProgramController {
    @Resource
    ProgramMapper programMapper;
    @Resource
    PlanMapper planMapper;
    //增加节目
    @PostMapping
    public Result save(@RequestBody Program program) {
        program.setUpdateTime(DateTime.now());
        programMapper.insert(program);
        return Result.success(200,"增加成功",program);
    }
    //编辑节目
    @PutMapping
    public Result update(@RequestBody Program program) {
        if(program.getState() == 0) {   //判断节目是否正在使用
            program.setUpdateTime(DateTime.now());
            programMapper.updateById(program);
            return Result.success(200,"编辑成功",program);
        } else {
            return Result.error("该节目正在使用中");
        }
    }
    //删除节目
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Program> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("id",id);
        Program program = programMapper.selectOne(queryWrapper);
        if (program.getState() == 0) {  //该节目未使用
            programMapper.deleteById(id);
            return Result.success(200,"删除成功",id);
        } else {
            //删除使用了该节目的计划
            QueryWrapper<Plan> planQueryWrapper = new QueryWrapper<>();
            planQueryWrapper.select("*").eq("program_id",id);
            Plan plan = planMapper.selectOne(planQueryWrapper);
            planMapper.deleteById(plan);
            programMapper.deleteById(id);
            return Result.success(200,"删除成功",id);
        }
    }
    //查询节目
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String name,
                              @RequestParam (defaultValue = "") String resolutionId,
                              @RequestParam (defaultValue = "") Integer state) {
        LambdaQueryWrapper<Program> wrapper = Wrappers.<Program>lambdaQuery();

        if (StrUtil.isNotBlank(name) ||
                StrUtil.isNotBlank(resolutionId) ||
                !String.valueOf(state).equals(null)) {
            wrapper.like(Program::getName,name).like(Program::getResolutionId,resolutionId).like(Program::getState,state);
        }
        Page<Program> programPage = programMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(programPage);
    }
}
