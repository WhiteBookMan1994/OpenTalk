package com.dxc.opentalk.springtest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2020-02-13
 */
//@Component
public class A {
    private B b;

    //@Autowired
    public A(B b){this.b=b;}
}
