package com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern;


/**
 * 打八折优惠策略
 *
 * @author dxc
 * @date 2019/3/31
 */
public class DiscountStrategyB implements DiscountStrategy {
    public double calculateActualAmount(double totalPrice) {
        return 0.8 * totalPrice;
    }
}
