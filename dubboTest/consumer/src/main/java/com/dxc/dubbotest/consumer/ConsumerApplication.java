package com.dxc.dubbotest.consumer;

import com.dxc.dubbotest.service.GreetingService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author dingchenchen
 * @since 2020/8/7
 */
public class ConsumerApplication {

    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) {
        ReferenceConfig<GreetingService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-consumer"));
        reference.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));
        reference.setInterface(GreetingService.class);
       // reference.setFilter("com.dxc.dubbotest.consumer.ConsumerFilter");
        GreetingService service = reference.get();
        String message = service.sayHello("dubbo");
        System.out.println(message);
    }
}
