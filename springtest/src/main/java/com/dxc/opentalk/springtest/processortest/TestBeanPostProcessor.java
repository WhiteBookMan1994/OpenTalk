package com.dxc.opentalk.springtest.processortest;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * BeanPostProcessor 调用测试
 * 注意点：
 * 1、实现BeanPostProcessor的类必须定义为bean，覆盖的方法才能被正确调用
 * 2、BeanPostProcessor 之中的两个方法是针对bean的初始化回调方法前后执行的，它们均是在bean的属性被填充之后才执行的；
 *    bean的初始化回调方法被执行前，bean的属性字段也已经填充了
 * 3、postProcessBeforeProcessor 和 postProcessAfterProcessor 两个方法，Spring容器中所有的bean都会执行，
 *    所以提供了bean和beanName，可以指定对应的bean执行自定义逻辑
 * @author dingchenchen
 * @since 2021/2/5
 */
@Component
public class TestBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (beanName.equals("testBean")) {
            System.out.println("postProcessBeforeInitialization在 bean 的初始化回调方法执行之前执行了.....");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (beanName.equals("testBean")) {
            System.out.println("postProcessAfterInitialization在 bean 的初始化回调方法执行之后执行了.....");
        }
        return bean;
    }
}
