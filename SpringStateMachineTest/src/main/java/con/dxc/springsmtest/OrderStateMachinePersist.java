package con.dxc.springsmtest;

import con.dxc.springsmtest.model.OrderDO;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Component;

/**
 * 伪状态持久化设置状态机当前状态
 * @author dingchenchen
 * @since 2020/8/5
 */
@Component
public class OrderStateMachinePersist implements StateMachinePersist<OrderStates, Events, OrderDO> {
    @Override
    public void write(StateMachineContext<OrderStates, Events> context, OrderDO contextObj) throws Exception {
        //这里不做任何持久化工作
    }

    @Override
    public StateMachineContext<OrderStates, Events> read(OrderDO contextObj) throws Exception {
        StateMachineContext<OrderStates, Events> result = new DefaultStateMachineContext<OrderStates, Events>(OrderStates.getByCode(contextObj.getOrderStatus()),
                null, null, null, null, "orderMachine");
        return result;
    }
}
