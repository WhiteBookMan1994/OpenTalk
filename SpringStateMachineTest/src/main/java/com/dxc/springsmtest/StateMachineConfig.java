package com.dxc.springsmtest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import java.util.EnumSet;

/**
 * @author dingchenchen
 * @since 2020/7/27
 */
@Configuration
@EnableStateMachine
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStates, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<OrderStates, Events> config)
            throws Exception {
        config
                .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, Events> states)
            throws Exception {
        states
                .withStates()
                .initial(OrderStates.WAIT_PAY)
                .states(EnumSet.allOf(OrderStates.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStates, Events> transitions)
            throws Exception {
        transitions
                .withExternal()
                .source(OrderStates.WAIT_PAY).target(OrderStates.PAID).event(Events.PAY)
                .and()
                .withExternal()
                .source(OrderStates.PAID).target(OrderStates.WAIT_RECEIVE).event(Events.DELIVER)
                .and()
                .withExternal()
                .source(OrderStates.WAIT_RECEIVE).target(OrderStates.DONE).event(Events.CONFIRM_RECEIVE);
    }

    @Bean
    public StateMachineListener<OrderStates, Events> listener() {
        return new StateMachineListenerAdapter<OrderStates, Events>() {
            @Override
            public void stateChanged(State<OrderStates, Events> from, State<OrderStates, Events> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}
