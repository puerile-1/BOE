package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Announcement;
import com.example.springboot.mapper.AnnouncementMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    @Resource
    AnnouncementMapper announcementMapper;

    //增加公告
    @PostMapping
    public Result save(@RequestBody Announcement announcement) {
        if (announcement.getColor() == announcement.getBgColor()) {     //判断字体颜色与背景颜色是否相同
            return Result.error("字体颜色与背景颜色不能相同");
        } else {
            announcementMapper.insert(announcement);
            return Result.success(200,"增加成功",announcement);
        }
    }

    //编辑公告
    @PutMapping
    public Result update(@RequestBody Announcement announcement) {
        QueryWrapper<Announcement> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("id",announcement.getId());
        Announcement announcement1 = announcementMapper.selectOne(wrapper);
        if (announcement1.getState() == 0) {     //判断公告是否已经发布
            return Result.error("公告已发布，不能编辑");
        }
        announcementMapper.updateById(announcement);
        return Result.success(200,"更新成功",announcement);
    }
    //下线公告
    @PostMapping("/{id}")
    public Result offline(@PathVariable Integer id) {
        //通过id查询公告
        QueryWrapper<Announcement> wrapper = new QueryWrapper<>();
        wrapper.select("*").eq("id",id);
        Announcement announcement = announcementMapper.selectOne(wrapper);
        if (announcement.getState() == 0) { //公告状态为上线，则将公告状态设置为下线
            announcement.setState(1);
            announcementMapper.updateById(announcement);
            return Result.success(200,"下线成功",id);
        } else {
            return Result.error("该公告未上线");
        }
    }

    //删除公告
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        QueryWrapper<Announcement> announcementQueryWrapper = new QueryWrapper<>();
        announcementQueryWrapper.select("*").eq("id",id);
        Announcement announcement = announcementMapper.selectOne(announcementQueryWrapper);
        if (announcement.getState() == 0) {     //判断公告是否已经发布
            return Result.error("公告已发布，不能删除");
        }
        announcementMapper.deleteById(id);
        return Result.success(200,"删除成功",id);
    }

    //查询公告
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String state) {
        LambdaQueryWrapper<Announcement> announcementLambdaQueryWrapper = Wrappers.<Announcement>lambdaQuery();
        if (StrUtil.isNotBlank(state)) {
            announcementLambdaQueryWrapper.like(Announcement::getState,state);
        }
        Page<Announcement> announcementPage = announcementMapper.selectPage(new Page<>(pageNum,pageSize),announcementLambdaQueryWrapper);
        return Result.success(announcementPage);
    }
}
