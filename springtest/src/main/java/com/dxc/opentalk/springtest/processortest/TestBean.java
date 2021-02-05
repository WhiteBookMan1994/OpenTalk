package com.dxc.opentalk.springtest.processortest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author dingchenchen
 * @since 2021/2/4
 */
@Component
public class TestBean {

    @Autowired
    TestBean1 testBean1;

    /**
     * 采用@PostConstruct注解定义的bean的初始化回调方法
     */
    @PostConstruct
    public void test() {
        testBean1.sayHi();
    }
}
