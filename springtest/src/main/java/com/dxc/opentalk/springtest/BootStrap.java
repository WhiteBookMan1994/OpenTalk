package com.dxc.opentalk.springtest;

import com.dxc.opentalk.springtest.service.H;
import com.dxc.opentalk.springtest.service.HH;
import com.dxc.opentalk.springtest.service.X;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
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
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(H.class);
        beanFactory.registerBeanDefinition("h", beanDefinition);
        H h = (H) applicationContext.getBean("h");
        h.test();
    }
}
