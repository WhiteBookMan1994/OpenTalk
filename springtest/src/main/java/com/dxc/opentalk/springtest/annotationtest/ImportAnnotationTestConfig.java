package com.dxc.opentalk.springtest.annotationtest;

import com.dxc.opentalk.notscan.NotScanBeanConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author dingchenchen
 * @since 2021/3/30
 */
@Configuration
@Import(NotScanBeanConfig.class)
public class ImportAnnotationTestConfig {


}
