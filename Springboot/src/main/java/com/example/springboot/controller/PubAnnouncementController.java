package com.example.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.example.springboot.common.Result;
import com.example.springboot.entity.PubAnnouncement;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/pubAnnouncement")
public class PubAnnouncementController {
    //新增
    @PostMapping
    public Result save(@RequestBody PubAnnouncement pubNotice){

        ConcurrentHashMap<String, WebSocket> webSocketMap = WebSocket.getWebSocketMap();
        for(WebSocket item :webSocketMap.values()){
            item.sendMessage("1"+ JSON.toJSONString(pubNotice));
        }
//        System.out.println(JSON.toJSONString(pubNotice));
        return Result.success(200,"发布成功",pubNotice);
    }
}
