package com.dxc.opentalk.springtest;

import com.dxc.opentalk.springtest.service.X;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
@EnableAspectJAutoProxy
@ComponentScan("com.dxc.opentalk.springtest")
public class BootStrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(BootStrap.class);
        X x = (X) applicationContext.getBean("x");
        x.test();
    }
}
