package com.dcc.openTalk.designPattern.strategyPattern.springProject;

import com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern.DiscountStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 策略上下文
 * @author dxc
 * @date 2019/3/31
 */
@Component("strategyContext")
public class StrategyContext {

    @Resource(name = "discountStrategyMap")
    private Map<Integer,DiscountStrategy> discountStrategyMap;

    public double doAction(int type, double totalPrice) {
        return this.discountStrategyMap.get(type).calculateActualAmount(totalPrice);
    }
}
