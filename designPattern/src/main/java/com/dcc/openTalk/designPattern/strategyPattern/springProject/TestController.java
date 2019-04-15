package com.dcc.openTalk.designPattern.strategyPattern.springProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * @author dxc
 * @date 2019/4/15 0015
 */
@RestController
@RequestMapping("/strategyPattern")
public class TestController {

    @Autowired
    private StrategyContext strategyContext;

    @GetMapping("/test")
    public double calculateActualAmount(@RequestParam(name = "totalPrice") double totalPrice,
                                        @RequestParam(name = "type") int type){
        return strategyContext.doAction(type, totalPrice);
    }
}
