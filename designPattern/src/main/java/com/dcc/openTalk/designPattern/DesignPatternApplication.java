package com.dcc.openTalk.designPattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dcc.openTalk.designPattern")
@SpringBootApplication
public class DesignPatternApplication {
    public static void main(String []args){
        SpringApplication.run(DesignPatternApplication.class);
    }
}
