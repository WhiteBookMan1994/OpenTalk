package con.dxc.springsmtest.controller;

import con.dxc.springsmtest.Events;
import con.dxc.springsmtest.OrderStateMachineBuilder;
import con.dxc.springsmtest.OrderStates;
import con.dxc.springsmtest.mapper.OrderMapper;
import con.dxc.springsmtest.model.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.AbstractStateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


/**
 * @author dingchenchen
 * @since 2020/8/4
 */
@RestController
@RequestMapping(path = "test")
public class OrderController {

    @Autowired
    OrderStateMachineBuilder orderStateMachineBuilder;

    @Resource(name = "orderPersister")
    private StateMachinePersister<OrderStates, Events, OrderDO> orderPersister;

    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/order/create")
    public String createOrder() {
        OrderDO order = new OrderDO();
        order.setAddress("中国北京");
        order.setAmount(6800);
        order.setOrderStatus(10);
        order.setPhone("18856731111");
        order.setUserId("YYhsqwer");
        order.setOrderNo(String.valueOf(System.currentTimeMillis()));
        orderMapper.insert(order);
        return "create success !";
    }

    @GetMapping("/order/pay")
    public String payOrder(@RequestParam String orderNo) {
        OrderDO orderDO = orderMapper.selectByOrderNo(orderNo);
        try {
            StateMachine<OrderStates, Events> stateMachine = orderStateMachineBuilder.build();
            stateMachine.start();
            Message<Events> message = MessageBuilder.withPayload(Events.PAY)
                    .setHeader("orderDO", orderDO)
                    .setHeader("payChannel", "AliPay").build();
            stateMachine.sendEvent(message);
            OrderStates orderState = stateMachine.getState().getId();
            System.out.println("处理后订单状态：" + orderState);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pay success !";
    }

    /**
     * 工厂创建的状态机状态是初始状态，这样写状态机不会触发Events.DELIVER事件
     * 因为当前状态是初始状态
     */
    @GetMapping("/order/deliver1")
    public String deliverOrder1(@RequestParam String orderNo) throws Exception {
        OrderDO orderDO = orderMapper.selectByOrderNo(orderNo);
        StateMachine<OrderStates, Events> stateMachine = orderStateMachineBuilder.build();
        stateMachine.start();
        Message<Events> message = MessageBuilder.withPayload(Events.DELIVER)
                .setHeader("orderDO", orderDO)
                .setHeader("payChannel", "AliPay").build();
        stateMachine.sendEvent(message);
        OrderStates orderState = stateMachine.getState().getId();
        System.out.println("处理后订单状态：" + orderState);
        return "deliver success !";
    }

    /**
     * 利用持久化接口的restore方法，根据orderDO中的状态设置状态机当前状态
     */
    @GetMapping("/order/deliver2")
    public String deliverOrder2(@RequestParam String orderNo) throws Exception {
        OrderDO orderDO = orderMapper.selectByOrderNo(orderNo);
        StateMachine<OrderStates, Events> stateMachine = orderStateMachineBuilder.build();
        try {
            orderPersister.restore(stateMachine, orderDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("根据订单状态设置后的状态机当前状态：" + stateMachine.getState().getId());
        Message<Events> message = MessageBuilder.withPayload(Events.DELIVER)
                .setHeader("orderDO", orderDO)
                .setHeader("expressNo", "SF12435678").build();
        stateMachine.sendEvent(message);
        OrderStates orderState = stateMachine.getState().getId();
        System.out.println("处理后订单状态：" + orderState);
        orderDO.setOrderStatus(orderState.getCode());
        orderMapper.update(orderDO);
        return "deliver success !";
    }

    /**
     * 利用StateMachineAccess<S, E>接口resetStateMachine方法，重新设置状态机上下文的方法是可以设置当前状态的
     * 其实反持久化接口 orderPersister.restore 方法内部就是这样恢复状态当前上下文的
     */
    @GetMapping("/order/deliver3")
    public String deliverOrder3(@RequestParam String orderNo) throws Exception {
        OrderDO orderDO = orderMapper.selectByOrderNo(orderNo);
        StateMachine<OrderStates, Events> stateMachine = orderStateMachineBuilder.build();
        StateMachineContext<OrderStates, Events> stateMachineContext = new DefaultStateMachineContext<OrderStates, Events>(new ArrayList<>(), OrderStates.getByCode(orderDO.getOrderStatus()),
                null, null, null, null, stateMachine.getId());
        try {
            // 设置状态机实例当前状态
            stateMachine.getStateMachineAccessor().doWithAllRegions(function -> function.resetStateMachine(stateMachineContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 别忘了start 状态机，否则也不会触发状态流转
        stateMachine.start();
        System.out.println("根据订单状态设置后的状态机当前状态：" + stateMachine.getState().getId());
        Message<Events> message = MessageBuilder.withPayload(Events.DELIVER)
                .setHeader("orderDO", orderDO)
                .setHeader("expressNo", "SF12435678").build();
        stateMachine.sendEvent(message);
        OrderStates orderState = stateMachine.getState().getId();
        System.out.println("处理后订单状态：" + orderState);
        orderDO.setOrderStatus(orderState.getCode());
        orderMapper.update(orderDO);
        return "deliver success !";
    }
}
