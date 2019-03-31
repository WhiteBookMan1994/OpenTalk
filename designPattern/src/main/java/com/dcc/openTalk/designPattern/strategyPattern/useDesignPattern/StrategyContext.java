package com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern;

/**
 * 策略上下文
 * @author dxc
 * @date 2019/3/31
 */
public class StrategyContext {

    private DiscountStrategy discountStrategy;

    public StrategyContext(DiscountStrategy discountStrategy){
        this.discountStrategy = discountStrategy;
    }

    public double getActualPrice(double totalPrice){
        return discountStrategy.calculateActualAmount(totalPrice);
    }
}
