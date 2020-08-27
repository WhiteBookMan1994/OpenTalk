package con.dxc.springsmtest;

import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.stereotype.Component;

/**
 * @author dingchenchen
 * @since 2020/8/4
 */
@Component
public class OrderStateMachineEventListener<OrderStates,Events> extends StateMachineListenerAdapter<OrderStates,Events> {

    @Override
    public void stateContext(StateContext<OrderStates,Events> stateContext) {
        //System.out.println("stateContext 的值:" + stateContext.toString());
    }
}
