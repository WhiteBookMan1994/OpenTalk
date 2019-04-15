package com.dcc.openTalk.designPattern.strategyPattern.springProject;


import com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern.DiscountStrategy;
import org.springframework.stereotype.Service;

/**
 * 打八折优惠策略
 *
 * @author dxc
 * @date 2019/3/31
 */
@Service("discountStrategyB")
public class DiscountStrategyB implements DiscountStrategy {
    public double calculateActualAmount(double totalPrice) {
        return 0.8 * totalPrice;
    }
}
