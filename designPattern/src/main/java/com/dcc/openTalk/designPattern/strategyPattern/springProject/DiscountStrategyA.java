package com.dcc.openTalk.designPattern.strategyPattern.springProject;

import com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern.DiscountStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * 原价处理
 *
 * @author dxc
 * @date 2019/3/31
 */
@Service("discountStrategyA")
public class DiscountStrategyA implements DiscountStrategy {
    public double calculateActualAmount(double totalPrice) {
        return totalPrice;
    }
}
