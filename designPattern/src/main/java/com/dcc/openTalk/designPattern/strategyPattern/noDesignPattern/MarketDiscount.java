package com.dcc.openTalk.designPattern.strategyPattern.noDesignPattern;

/**
 * 场景：商场打折优惠
 * 原价、打八折、满99减50
 * 普通实现如下
 *
 * @author dxc
 * @date 2019/3/30
 */
public class MarketDiscount {

    /**
     * 计算实付金额
     *
     * @param totalPrice   商品总额
     * @param discountType 打折类型
     * @return
     */
    public static double calculateActualAmount(double totalPrice, int discountType) {
        switch (discountType){
            //原价
            case 0:
                break;
            //打八折
            case 1:
                totalPrice = totalPrice * 0.8;
                break;
            //满99减50
            case 2:
                if (totalPrice >= 99) {
                    totalPrice = totalPrice - 50;
                }
                break;
                default:
        }
        return totalPrice;
    }

    public static void main(String []args){
        System.out.println(calculateActualAmount(2019.00, 0));
        System.out.println(calculateActualAmount(2019.00, 1));
        System.out.println(calculateActualAmount(2019.00, 2));
    }
}
