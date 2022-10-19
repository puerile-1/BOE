package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.File;
import com.example.springboot.entity.Material;
import com.example.springboot.mapper.FileMapper;
import com.example.springboot.mapper.MaterialMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FileMapper fileMapper;
    @Resource
    MaterialMapper materialMapper;
    //新增
    @PostMapping
    public Result add(@RequestBody File file) {
        if(file.getFileId() == null){
            file.setChecked(false);
            file.setDisplayed(false);
            file.setMyStyle("display:none");
        }
        fileMapper.insert(file);
        return Result.success(200,"增加成功",file);
    }
    //更新
    @PutMapping
    public Result update(@RequestBody File file) {
        fileMapper.updateById(file);
        return Result.success(200,"更新成功",file);
    }
    //删除
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Material> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("*").eq("fileId",id);
        List<Material> result = materialMapper.selectList(queryWrapper);
        if(result == null && result.size() == 0) {  //判断当前文件夹是否为空
            fileMapper.deleteById(id);
            return Result.success(200,"删除成功",id);
        } else {
            //删除当前文件夹内的所有素材
            materialMapper.delete(queryWrapper);
            //删除文件夹
            fileMapper.deleteById(id);
            return Result.success(200,"删除成功",id);
        }
    }
    //查询
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String search){
        LambdaQueryWrapper<File> wrapper =  Wrappers.<File>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(File::getFileName,search);
        }
        Page<File> filePage = fileMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(filePage);
    }


}
