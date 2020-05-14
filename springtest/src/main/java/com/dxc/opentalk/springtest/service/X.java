package com.dxc.opentalk.springtest.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
//@Component("x")
public class X implements  DisposableBean {

    //@Autowired
    //private Y y;

    private String s="123";

    public X() {
        System.out.println("Construct X");
    }


    public void customInit() {
        System.out.println("init-method exec...");
    }

    //@Override
    /*public void afterPropertiesSet() throws Exception {
        System.out.println("初始化回调方法开始....");
        System.out.println(this.getS());
        y.test();
        s="afterPropertiesSet";
        System.out.println("InitializingBean exec...");
        System.out.println("初始化回调方法结束...");
    }*/

    @PostConstruct
    public void annotationInit() {
        System.out.println("@PostConstruct exec...");
    }

    public void destroy() throws Exception {
        System.out.println("DisposableBean exec...");
    }

    public void customDestroy() {
        System.out.println("destroy-method exec...");
    }

    @PreDestroy
    public void annotationDestroy() {
        System.out.println("@PreDestroy exec...");
    }



    public void setS(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }

    public void test(){
        //y.test();
    }
}
