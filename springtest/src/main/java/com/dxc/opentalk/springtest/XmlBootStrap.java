package com.dxc.opentalk.springtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author dingchenchen
 * @since 2020-02-29
 */
public class XmlBootStrap {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new
                ClassPathXmlApplicationContext("applicationContext.xml");
        applicationContext.close();
    }
}
