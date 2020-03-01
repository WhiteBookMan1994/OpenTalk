package com.dxc.opentalk.springtest.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
public class X implements InitializingBean, DisposableBean {

    @Autowired
    private Y y;

    private String s;

    public X() {
        System.out.println("Construct X");
    }


    public void customInit() {
        System.out.println("init-method exec...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean exec...");
    }

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

}
