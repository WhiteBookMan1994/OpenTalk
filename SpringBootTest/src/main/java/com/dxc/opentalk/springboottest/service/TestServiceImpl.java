package com.dxc.opentalk.springboottest.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * @author dingchenchen
 * @since 2020-05-01
 */
@Service
public class TestServiceImpl implements TestService{
    @Override
    public CompletableFuture<String> test() {
        System.out.println("ThreadName:" + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试Spring 异步调用！");
        return CompletableFuture.supplyAsync(() -> {
            return "test";
        });
    }

/*    @Override
    public void test2() {
        test();
    }*/
}
