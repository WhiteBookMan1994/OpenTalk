package con.dxc.springsmtest;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.StateMachineBuilder;
import org.springframework.statemachine.guard.Guard;

import java.util.EnumSet;

/**
 * @author dingchenchen
 * @since 2020/8/4
 */
@Configuration
public class OrderStateMachineBuilder {

    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    OrderStateMachineEventListener orderStateMachineEventListener;

    /**
     * 构建状态机
     *
     * @return
     * @throws Exception
     */
    public StateMachine<OrderStates, Events> build() throws Exception {
        StateMachineBuilder.Builder<OrderStates, Events> builder = StateMachineBuilder.builder();

        System.out.println("构建订单状态机");

        builder.configureConfiguration()
                .withConfiguration()
                .beanFactory(beanFactory)
               // .machineId(String.valueOf(new char[1024 * 1024 * 2]))
                .machineId("orderStateMachine")
                .listener(orderStateMachineEventListener);

        builder.configureStates()
                .withStates()
                .initial(OrderStates.WAIT_PAY)
                .states(EnumSet.allOf(OrderStates.class));

        builder.configureTransitions()
                .withExternal()
                .source(OrderStates.WAIT_PAY).target(OrderStates.PAID)
                .event(Events.PAY).guard(new Guard<OrderStates, Events>() {
            @Override
            public boolean evaluate(StateContext<OrderStates, Events> context) {
                return false;
            }
        }).action(action())
                .and()
                .withExternal()
                .source(OrderStates.PAID).target(OrderStates.WAIT_RECEIVE)
                .event(Events.DELIVER)
                .and()
                .withExternal()
                .source(OrderStates.WAIT_RECEIVE).target(OrderStates.DONE)
                .event(Events.CONFIRM_RECEIVE);

         StateMachine<OrderStates, Events> statemachine =  builder.build();
        System.out.println("statemachine对象:"+"@" + Integer.toHexString(statemachine.hashCode()));
         return statemachine;
    }

    @Bean
    public Action<OrderStates, Events> action() {
        return new Action<OrderStates, Events>() {

            @Override
            public void execute(StateContext<OrderStates, Events> context) {
                System.out.println(context);
            }
        };
    }
}
