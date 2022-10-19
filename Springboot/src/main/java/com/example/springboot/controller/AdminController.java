package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Admin;
import com.example.springboot.entity.Login;
import com.example.springboot.mapper.AdminMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminMapper adminMapper;
    //新增
    @PostMapping
    public Result save(@RequestBody Admin admin){
        if(admin.getAdminId() == null){
            admin.setPwd("123456");
            admin.setInstitutionsId("城院罗老师测试");
            admin.setUserState("启用");
        }
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("admin_Name",admin.getAdminName());
        Admin admin1 = adminMapper.selectOne(queryWrapper);
        if (admin1 != null) {
            return Result.error("该用户名已存在");
        }
        adminMapper.insert(admin);
        return Result.success(200,"增加成功",admin);
    }
    //更新
    @PutMapping
    public Result update(@RequestBody Admin admin){
        adminMapper.updateById(admin);
        return Result.success(200,"更新成功",admin);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        adminMapper.deleteById(id);
        return Result.success(200,"删除成功",id);
    }
    //查询
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String institutionsId,
                              @RequestParam (defaultValue = "") String adminName,
                              @RequestParam (defaultValue = "") String userState,
                              @RequestParam (defaultValue = "") String role){
        LambdaQueryWrapper<Admin> wrapper =  Wrappers.<Admin>lambdaQuery();
        if (StrUtil.isNotBlank(adminName) || StrUtil.isNotBlank(userState) || StrUtil.isNotBlank(role)){
            wrapper.like(Admin::getAdminName,adminName).like(Admin::getUserState,userState).like(Admin::getRole,role).like(Admin::getInstitutionsId,institutionsId);
        }
        Page<Admin> userPage = adminMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(userPage);
    }

    //登录
    @PostMapping("/login")
    public Result login(@RequestBody Login login){
        //查询相应用户名密码是否存在
        Admin res = adminMapper.selectOne(Wrappers.<Admin>lambdaQuery().eq(Admin::getAdminId,login.getUsername()).eq(Admin::getPwd,login.getPassword()));
        if(res == null){
            return Result.error("用户名密码错误！");
        } else if (res.getUserState().equals("禁用")) {       //查看用户状态
            return Result.error("该用户已被禁用");
        }
        return Result.success(res);
    }

}
