package com.dxc.opentalk.springtest.annotationtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dingchenchen
 * @since 2021/4/1
 */
//@Transactional
@Service
public class TransactionalAnnotationTest {

    /**
     * 装配自身代理bean
     * transactionalAnnotationTest.test1() 解决自调用事务不生效问题
     */
    @Autowired
    TransactionalAnnotationTest transactionalAnnotationTest;

    @Transactional
    public void test(){
        System.out.println("--");
    }

    public void test1(){
        System.out.println("--");
    }
}
