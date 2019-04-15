package com.dcc.openTalk.designPattern.strategyPattern.springProject;

import com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern.DiscountStrategy;
import org.springframework.stereotype.Service;

/**
 * 满99减50
 *
 * @author dxc
 * @date 2019/3/31
 */
@Service("discountStrategyC")
public class DiscountStrategyC implements DiscountStrategy{
    public double calculateActualAmount(double totalPrice) {
        if (totalPrice >= 99) {
            totalPrice = totalPrice - 50;
        }
        return totalPrice;
    }
}
