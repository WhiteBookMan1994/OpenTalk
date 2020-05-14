package com.dxc.opentalk.springtest.service;

import com.dxc.opentalk.springtest.config.A;
import com.dxc.opentalk.springtest.config.B;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2020-04-16
 */
@Component
public class XBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)throws BeansException {
        if (beanName.equals("x")){
            X x = (X)bean;
            System.out.println("x:before:s=" + x.getS());
            x.test();
            x.setS("s=postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName){
        if (beanName.equals("x")){
            X x = (X)bean;
            System.out.println("x:after:s=" + x.getS());
            x.setS("s=postProcessAfterInitialization");
        }
        return bean;
    }
}
