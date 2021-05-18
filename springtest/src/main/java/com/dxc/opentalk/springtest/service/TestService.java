package com.dxc.opentalk.springtest.service;

import org.springframework.stereotype.Service;

/**
 * @author dingchenchen
 * @since 2021/4/15
 */
@Service
public class TestService {

    public String test(){
        System.out.println("test() execute ...");
        return "hello world";
    }
}
