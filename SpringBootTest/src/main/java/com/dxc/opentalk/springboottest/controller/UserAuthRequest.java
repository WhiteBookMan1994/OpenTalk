package com.dxc.opentalk.springboottest.controller;

import lombok.Data;

/**
 * @author dingchenchen
 * @since 2020/7/8
 */
@Data
public class UserAuthRequest {
    /**
     * 平台appId
     */
    private String appId;

    /**
     * 平台secretId
     */
    private String secretId;

    /**
     * 签名串
     */
    private String signature;

    /**
     * 时间戳
     */
    private Long timestamp;
}
