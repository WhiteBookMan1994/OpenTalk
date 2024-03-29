package com.dxc.opentalk.springtest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2021/4/15
 */
@Aspect
@Component
public class AopConfig {

    @Before("execution (* com.dxc.opentalk.springtest.service.TestService.*())")
    public void before()  {
        System.out.println("AopConfig before advice begin ...");
    }

    @Around("execution (* com.dxc.opentalk.springtest.service.TestService.*())")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("AopConfig around advice begin ...");
        joinPoint.proceed();
        System.out.println("AopConfig around advice end ...");
    }

    @After("execution (* com.dxc.opentalk.springtest.service.TestService.*())")
    public void after()  {
        System.out.println("AopConfig after advice begin ...");
    }
}
