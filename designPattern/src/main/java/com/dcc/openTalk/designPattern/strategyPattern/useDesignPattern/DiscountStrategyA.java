package com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern;

/**
 * 原价处理
 *
 * @author dxc
 * @date 2019/3/31
 */
public class DiscountStrategyA implements DiscountStrategy {
    public double calculateActualAmount(double totalPrice) {
        return totalPrice;
    }
}
