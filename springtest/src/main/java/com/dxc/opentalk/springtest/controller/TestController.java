package com.dxc.opentalk.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dxc")
public class TestController {

    @PostMapping("/test")
    public String testListAndMap(@RequestParam List<String> list, @RequestBody Map<Integer,String> map){
        System.out.println("list:" + list);
        System.out.println("map:" + map);
        return "success";
    }

    @GetMapping("/test1")
    public String test(){
        return "success";
    }
}
