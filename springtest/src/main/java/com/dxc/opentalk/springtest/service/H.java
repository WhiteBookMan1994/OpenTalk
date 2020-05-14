package com.dxc.opentalk.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dingchenchen
 * @since 2020-05-02
 */
public class H {

    private String s ;

    @Autowired
    Y y;

    public void test(){
        y.test();
        System.out.println("hello world!");
    }

    public void setS(String s) {
        this.s = s;
    }
}
