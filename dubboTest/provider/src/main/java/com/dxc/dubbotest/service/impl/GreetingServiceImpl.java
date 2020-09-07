package com.dxc.dubbotest.service.impl;

import com.dxc.dubbotest.service.GreetingService;

/**
 * @author dingchenchen
 * @since 2020/8/7
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHello(String name) {
        return "hello, " + name;
    }
}
