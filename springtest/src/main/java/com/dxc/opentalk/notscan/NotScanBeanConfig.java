package com.dxc.opentalk.notscan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dingchenchen
 * @since 2021/3/30
 */
@Configuration
public class NotScanBeanConfig {

    @Bean
    public NotScan notScan(){
        return new NotScan();
    }
}
