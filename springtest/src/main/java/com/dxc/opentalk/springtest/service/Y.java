package com.dxc.opentalk.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
public class Y {

    @Autowired
    private X x;

    public Y() {
        System.out.println("Construct Y");
    }

    public void test(){
        System.out.println("y.test()...");
    }
}
