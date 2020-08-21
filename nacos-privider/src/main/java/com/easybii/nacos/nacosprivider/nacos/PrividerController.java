package com.easybii.nacos.nacosprivider.nacos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: cly902
 * @version: 1.0
 * @modified By:cly902
 */
@RestController
@RefreshScope
@Api("第一个接口")
public class PrividerController {

    @Value("${myName}")
    private String myName;

    @ApiOperation("第一个测试接口")
    @GetMapping("/hello")
    public String hello(){
        return "hello----->" + myName +"--->" ;
    }
}
