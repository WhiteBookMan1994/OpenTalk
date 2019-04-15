package com.dcc.openTalk.designPattern.strategyPattern.springProject;

import com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern.DiscountStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dxc
 * @date 2019/4/15 0015
 */
@Configuration
public class Config {

    @Autowired
    private DiscountStrategyA discountStrategyA;

    @Autowired
    private DiscountStrategyB discountStrategyB;

    @Autowired
    private DiscountStrategyC discountStrategyC;

    @Bean
    public Map<Integer, DiscountStrategy> discountStrategyMap(){
        Map<Integer, DiscountStrategy> map = new HashMap<>();
        map.put(1, discountStrategyA);
        map.put(2, discountStrategyB);
        map.put(3, discountStrategyC);
        return map;
    }
}
