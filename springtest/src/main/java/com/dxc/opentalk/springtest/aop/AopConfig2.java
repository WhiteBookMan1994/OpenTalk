package com.dxc.opentalk.springtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2021/4/15
 */
@Aspect
@Order(2)
@Component
public class AopConfig2 {

    @Before("execution (* com.dxc.opentalk.springtest.service.TestService.*())")
    public void before()  {
        System.out.println("AopConfig2 before advice begin ...");
    }

    @Around("execution (* com.dxc.opentalk.springtest.service.TestService.*())")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AopConfig2 around advice begin ...");
        joinPoint.proceed();
        System.out.println("AopConfig2 around advice end ...");
    }

    @After("execution (* com.dxc.opentalk.springtest.service.TestService.*())")
    public void after()  {
        System.out.println("AopConfig2 after advice begin ...");
    }
}
