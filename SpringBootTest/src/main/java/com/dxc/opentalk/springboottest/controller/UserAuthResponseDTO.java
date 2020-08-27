package com.dxc.opentalk.springboottest.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingchenchen
 * @since 2020/7/7
 */
@Data
public class UserAuthResponseDTO implements Serializable {
    private static final long serialVersionUID = -965340693068114593L;

    /**
     * uid
     */
    private Long uid;

    /**
     * userId
     */
    private String dscId;

    /**
     * 名称
     */
    private String name;

    /**
     * 1:个人 2：全店
     */
    private Integer auth;

    /**
     * 用户链金开通状态， 0:关闭 1：开启
     */
    private Integer isOpen;
}
