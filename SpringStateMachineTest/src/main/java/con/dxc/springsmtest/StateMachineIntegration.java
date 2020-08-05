package con.dxc.springsmtest;

import con.dxc.springsmtest.mapper.OrderMapper;
import con.dxc.springsmtest.model.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.annotation.WithStateMachine;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dingchenchen
 * @since 2020/8/4
 */
@WithStateMachine(id = "orderMachineId")
public class StateMachineIntegration {

    @Autowired
    OrderMapper orderMapper;

    @OnTransition(target = "PAID")
    public void create(){
        System.out.println("订单支付成功！");
    }

    @OnTransition(source = "WAIT_PAY", target= "PAID")
    @Transactional(rollbackFor = Exception.class)
    public void pay(Message<Events> message) {
        OrderDO orderDO = (OrderDO) message.getHeaders().get("orderDO");
        System.out.println("传递的orderDO:" + orderDO);
        String payChannel = (String) message.getHeaders().get("payChannel");
        System.out.println("传递的payChannel:" + payChannel);
        orderDO.setOrderStatus(OrderStates.PAID.getCode());
        orderMapper.update(orderDO);
    }

    @OnTransition(source = "PAID", target= "PAID")
    public void deliver(Message<Events> message) {
        OrderDO orderDO = (OrderDO) message.getHeaders().get("orderDO");
        System.out.println("传递的orderDO:" + orderDO);
        String expressNo = (String) message.getHeaders().get("expressNo");
        System.out.println("发货快递单号:" + expressNo);
    }
}
