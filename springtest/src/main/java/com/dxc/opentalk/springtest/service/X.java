package com.dxc.opentalk.springtest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
@Component
public class X {

    @Autowired
    private Y y;

    public X() {
        System.out.println("Construct X");
    }


    public void test() {
        y.test();
        System.out.println("test exec...");
    }
}
