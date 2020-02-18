package com.dxc.opentalk.springboottest;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
@ComponentScan(value= "com.dxc.opentalk.springboottest")
@SpringBootApplication
public class BootStrap implements ApplicationContextAware {

    static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(BootStrap.class);
        System.out.println(BootStrap.applicationContext.getBean("x"));
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BootStrap.applicationContext =applicationContext;
    }
}
