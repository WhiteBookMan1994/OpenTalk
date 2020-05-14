package com.dxc.opentalk.springboottest.service;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

/**
 * @author dingchenchen
 * @since 2020-05-01
 */
public interface TestService {
    @Async
    CompletableFuture<String> test();

   // void test2();
}
