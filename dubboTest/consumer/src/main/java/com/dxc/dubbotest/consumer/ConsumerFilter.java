package com.dxc.dubbotest.consumer;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

/**
 * @author dingchenchen
 * @since 2020/8/7
 */
@Activate(group = {CommonConstants.CONSUMER})
public class ConsumerFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        System.out.println("say hello 之前照照镜子......");
        return invoker.invoke(invocation);
    }
}
