package com.example.springboot.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Material;
import com.example.springboot.mapper.MaterialMapper;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/material")
public class MaterialController {
    @Resource
    MaterialMapper materialMapper;


    @CrossOrigin
    @PostMapping("/UploadPic")
    public Result UploadPic(MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        try {
            File staticDir = new File (ResourceUtils.getURL("classpath:static").getPath());
            System.out.println(staticDir);
        } catch (FileNotFoundException e) {
            // static 目录不存在！
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy-MM-DD/");
        String filename = file.getOriginalFilename();//取得文件名称
        if (!filename.endsWith(".png") && !filename.endsWith(".jpg")) {
            return Result.error("文件类型不对");
        }

        String format = dateFormat.format(new Date());
        System.out.printf(format);
        //realPath为文件保存的位置
        String path = ResourceUtils.getURL("classpath:").getPath()+"static/img"+format;
        String realPath = path.replace('/', '\\').substring(1,path.length());
        //用于查看路径是否正确
        System.out.println(realPath);
        java.io.File folder = new File(realPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String newName;
        if (filename.endsWith(".png"))
            newName = UUID.randomUUID().toString() + ".png";
        else
            newName = UUID.randomUUID().toString() + ".jpg";
        try {
            //新建文件
            file.transferTo(new File(folder, newName));
            //String url = realPath + newName;
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }

        //返回可供外界访问的url
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img" + format + newName;

        Material material = new Material();
        material.setMaterialName("上传图片");
        material.setData(url);
        material.setFileName("啧啧啧");
        material.setChecked(false);
        material.setDisplayed(false);
        material.setMyStyle("display:none;");
        materialMapper.insert(material);
        return Result.success(url);
    }


    //删除素材
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        materialMapper.deleteById(id);
        return Result.success(200,"删除成功",id);
    }
    //查询
    @GetMapping
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam (defaultValue = "10") Integer pageSize,
                              @RequestParam (defaultValue = "") String fileName){
        LambdaQueryWrapper<Material> wrapper =  Wrappers.<Material>lambdaQuery();
        if (StrUtil.isNotBlank(fileName)){
            wrapper.like(Material::getFileName,fileName);
        }
        Page<Material> materialPage = materialMapper.selectPage(new Page<>(pageNum,pageSize),wrapper);
        return Result.success(materialPage);
    }


}
