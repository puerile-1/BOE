package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.PubProgram;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/pubProgram")
public class PubProgramController {
    //新增
    @PostMapping
    public Result save(@RequestBody PubProgram pubProgram){
        ConcurrentHashMap<String, WebSocket> webSocketMap = WebSocket.getWebSocketMap();
        for(WebSocket item :webSocketMap.values()){
            item.sendMessage("0"+pubProgram.getPubProgramImg());
        }
        return Result.success(200,"发布成功",pubProgram);
    }
}
