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
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.support.AbstractStateMachine;
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

    @Resource(name="orderPersister")
    private StateMachinePersister<OrderStates, Events, OrderDO> orderPersister;

    @Autowired
    OrderMapper orderMapper;

    @GetMapping("/order/create")
    public String createOrder(){
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
    public String payOrder(@RequestParam String orderNo){
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

    @GetMapping("/order/deliver")
    public String deliverOrder(@RequestParam String orderNo){
        OrderDO orderDO = orderMapper.selectByOrderNo(orderNo);
        //List<StateMachine> machineList = new ArrayList<>();
        try {
            /*for(long i = 0; i<=2000 *1024000;i++){
                StateMachine<OrderStates, Events> stateMachine = orderStateMachineBuilder.build();
                System.out.println("溢出前size:"+ i);
               // machineList.add(stateMachine);
            }
            // StateMachine<OrderStates, Events> stateMachine = machineList.get(99);*/
            ReferenceQueue<StateMachine> referenceQueue = new ReferenceQueue<>();
           // StateMachine<OrderStates, Events> stateMachine = orderStateMachineBuilder.build();
           // System.out.println("生成的statemachine对象" + stateMachine);
            WeakReference<StateMachine> weakReference = new WeakReference<>( orderStateMachineBuilder.build(), referenceQueue);
            System.gc();
            Thread.sleep(5000);
            System.out.println("回收的对象："+referenceQueue.poll().get());
            /*orderPersister.restore(stateMachine, orderDO);
            System.out.println("根据订单状态设置后的状态机当前状态：" + stateMachine.getState().getId());
            Message<Events> message = MessageBuilder.withPayload(Events.DELIVER)
                    .setHeader("orderDO", orderDO)
                    .setHeader("expressNo", "SF12435678").build();
            stateMachine.sendEvent(message);
            OrderStates orderState = stateMachine.getState().getId();
            System.out.println("处理后订单状态：" + orderState);
            orderDO.setOrderStatus(orderState.getCode());
            orderMapper.update(orderDO);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "deliver success !";
    }
}
