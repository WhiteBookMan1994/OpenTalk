package com.dxc.opentalk.springboottest.controller;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dingchenchen
 * @since 2020/7/7
 */
@Data
public class McgjResponse<T> implements Serializable {

    private static final long serialVersionUID = 2827610990594060934L;

    /**
     * 错误码
     */
    private Integer error;

    /**
     * code: 0 成功
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String msg;

    /**
     * 链路id
     */
    private String traceId;

    /**
     * 结果数据
     */
    private T data;
}
