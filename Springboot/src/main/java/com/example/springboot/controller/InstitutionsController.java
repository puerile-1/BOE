package com.example.springboot.controller;

import com.example.springboot.mapper.InstitutionsMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/institutions")
public class InstitutionsController {
    @Resource
    InstitutionsMapper institutionsMapper;

}
