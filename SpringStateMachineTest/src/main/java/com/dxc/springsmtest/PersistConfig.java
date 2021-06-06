package com.dxc.springsmtest;

import com.dxc.springsmtest.model.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.StateMachinePersister;

/**
 * @author dingchenchen
 * @since 2020/8/5
 */
@Configuration
public class PersistConfig {
    @Autowired
    private OrderStateMachinePersist orderStateMachinePersist;

    @Bean(name="orderPersister")
    public StateMachinePersister<OrderStates, Events, OrderDO> orderPersister() {
        return new DefaultStateMachinePersister<OrderStates, Events, OrderDO>(orderStateMachinePersist);
    }
}
