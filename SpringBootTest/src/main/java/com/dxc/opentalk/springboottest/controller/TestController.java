package com.dxc.opentalk.springboottest.controller;

import com.dxc.opentalk.springboottest.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author dingchenchen
 * @since 2020-05-01
 */
@RestController
@RequestMapping("/test")
@EnableAsync
public class TestController implements ApplicationContextAware {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/testAsync")
    public void print() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        System.out.println("当前线程开始执行测试函数......");
        CompletableFuture<String> s = testService.test();
        try {
            System.out.println("s="+s.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
            if (i % 10 == 0) {
                System.out.println();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("当前线程测试函数执行完毕......");
    }
    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
        applicationContext.toString();
    }
}
