package com.dxc.opentalk.springboottest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dingchenchen
 * @since 2020/7/8
 */
@RestController
public class TestController1 {
///auth/getUserAuth?version=v1.0 @RequestBody UserAuthRequest request
    @PostMapping(value ="/auth/getUserAuth",params = "version=v1.0")
    public McgjResponse<UserAuthResponseDTO> test(){
        //System.out.println("request:" + request);
        McgjResponse<UserAuthResponseDTO> mcgjResponse = new McgjResponse<>();
        UserAuthResponseDTO userAuthResponseDTO = new UserAuthResponseDTO();
        userAuthResponseDTO.setAuth(1);
        userAuthResponseDTO.setDscId("test123");
        userAuthResponseDTO.setName("拉拉");
        userAuthResponseDTO.setUid(123L);
        mcgjResponse.setData(userAuthResponseDTO);
        mcgjResponse.setCode(200);
        mcgjResponse.setMsg("成功");
        mcgjResponse.setTraceId("traceId");
        return mcgjResponse;
    }
}
