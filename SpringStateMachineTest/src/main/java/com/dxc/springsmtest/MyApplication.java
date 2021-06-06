package com.dxc.springsmtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

/**
 * @author dingchenchen
 * @since 2020/7/27
 */
@SpringBootApplication
public class MyApplication implements CommandLineRunner {
    @Autowired
    private StateMachine<OrderStates, Events> stateMachine;

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    public void run(String... args) throws Exception {
       // stateMachine.sendEvent(Events.PAY);
    }
}
