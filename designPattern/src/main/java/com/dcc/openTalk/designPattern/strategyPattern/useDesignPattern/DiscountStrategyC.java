package com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern;

/**
 * 满99减50
 *
 * @author dxc
 * @date 2019/3/31
 */
public class DiscountStrategyC implements DiscountStrategy{
    public double calculateActualAmount(double totalPrice) {
        if (totalPrice >= 99) {
            totalPrice = totalPrice - 50;
        }
        return totalPrice;
    }
}
