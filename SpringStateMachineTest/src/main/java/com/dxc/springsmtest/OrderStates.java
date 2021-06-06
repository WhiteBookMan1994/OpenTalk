package com.dxc.springsmtest;

import lombok.Getter;

/**
 * @author dingchenchen
 * @since 2020/7/27
 */
@Getter
public enum OrderStates {
    //待支付
    WAIT_PAY(10),
    //已支付，待发货
    PAID(20),
    //待确认收货
    WAIT_RECEIVE(30),
    //订单完成
    DONE(40);
    private Integer code;

    OrderStates(Integer code){
        this.code = code;
    }

    public static OrderStates getByCode(Integer code){
        for(OrderStates orderStates: values()){
            if(orderStates.getCode().equals(code)){
                return orderStates;
            }
        }
        return null;
    }
}
