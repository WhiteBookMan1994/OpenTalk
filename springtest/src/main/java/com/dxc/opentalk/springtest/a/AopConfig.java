package com.dxc.opentalk.springtest.a;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2020-02-14
 */
//@Aspect
//@Component
public class AopConfig {

    @Before("execution(* com.dxc.opentalk.springtest.service.UserManager.*())")
    public void before(){
        System.out.println("before 执行");
    }

    @Pointcut("within(com.dxc.opentalk.springtest.service.X)")
    private void apply(){}

    @Before("apply()")
    public void check(){
        System.out.println("aop...");
    }

}
