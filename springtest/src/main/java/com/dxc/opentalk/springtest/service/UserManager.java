package com.dxc.opentalk.springtest.service;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2021/4/1
 */
@Component
public class UserManager {

    public void a(){
        System.out.println("a() 方法执行");
        ((UserManager) AopContext.currentProxy()).b();
    }

    public void b(){
        System.out.println("b() 方法执行");
    }
}
