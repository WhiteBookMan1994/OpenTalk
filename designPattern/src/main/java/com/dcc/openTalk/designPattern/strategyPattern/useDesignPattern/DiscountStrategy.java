package com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern;

/**
 * @author dxc
 * @date 2019/3/31
 */
public interface DiscountStrategy {

    /**
     * 计算实付金额
     *
     * @param totalPrice   商品总额
     * @return
     */
    double calculateActualAmount(double totalPrice);
}
