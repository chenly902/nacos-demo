package com.atguigu.nacos.nacosprivider.nacos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: cly902
 * @date: Created in 2020年7月3日
 * @version: 1.0
 * @modified By:cly902
 */
@RestController
@RefreshScope
public class PrividerController {

    @Value("${myName}")
    private String myName;

    @GetMapping("/hello")
    public String hello(){
        return "hello----->" + myName +"--->" ;
    }
}
