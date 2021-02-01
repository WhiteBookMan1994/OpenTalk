package com.dxc.opentalk.springboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author dingchenchen
 * @since 2020/12/31
 */
@RestController
@RequestMapping("/dxc")
public class RequestTest {

    @PostMapping("/test")
    public String testListAndMap(@RequestParam List<String> list, @RequestBody Map<Integer,String> map){
        System.out.println("list.size():" + list.size() + ":" + list);
        System.out.println(list.get(1).length());
        System.out.println("map:" + map);
        return "success";
    }

    @GetMapping("/test1")
    public String test(){
        return "success";
    }
}
