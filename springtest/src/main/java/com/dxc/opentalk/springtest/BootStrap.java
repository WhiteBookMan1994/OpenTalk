package com.dxc.opentalk.springtest;

import com.dxc.opentalk.springtest.service.TestService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dingchenchen
 * @since 2020-02-10
 */
@EnableTransactionManagement
@EnableAspectJAutoProxy
@ComponentScan("com.dxc.opentalk.springtest")
public class BootStrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext
               = new AnnotationConfigApplicationContext(BootStrap.class);
        TestService testService = (TestService) applicationContext.getBean("testService");
        testService.test();






        //userManager.b();

        /*DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(H.class);
        beanFactory.registerBeanDefinition("h", beanDefinition);
        H h = (H) applicationContext.getBean("h");
        h.test();*/

        //测试@Import注解
       /*NotScan n = (NotScan) applicationContext.getBean("notScan");
       n.show();*/
    }
}
