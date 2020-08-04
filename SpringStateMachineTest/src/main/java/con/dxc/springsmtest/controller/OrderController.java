package con.dxc.springsmtest.controller;

import con.dxc.springsmtest.Events;
import con.dxc.springsmtest.OrderStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试状态机的有状态特性
 * @author dingchenchen
 * @since 2020/7/30
 */
@RestController
public class OrderController {

    @Autowired
    private StateMachine<OrderStates, Events> stateMachine;

    @GetMapping("/order/pay")
    public String payOrder(){
        stateMachine.sendEvent(Events.PAY);
        System.out.println("sm current state:" + stateMachine.getState());
        return "pay success !";
}

    @GetMapping("/order/deliver")
    public String deliverOrder(){
        stateMachine.sendEvent(Events.DELIVER);
        System.out.println("sm current state:" + stateMachine.getState());
        return "deliver success !";
    }
}
