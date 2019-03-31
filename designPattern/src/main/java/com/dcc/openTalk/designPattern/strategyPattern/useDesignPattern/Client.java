package com.dcc.openTalk.designPattern.strategyPattern.useDesignPattern;

/**
 * 客户端调用
 * @author dxc
 * @date 2019/3/31
 */
public class Client {
    public static void main(String []args){
        StrategyContext context = new StrategyContext(new DiscountStrategyB());
        double actualPrice = context.getActualPrice(2019.00);
        System.out.println("优惠后实付:" + actualPrice);
    }
}
