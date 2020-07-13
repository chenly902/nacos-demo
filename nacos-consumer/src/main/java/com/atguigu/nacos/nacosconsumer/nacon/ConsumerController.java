package com.atguigu.nacos.nacosconsumer.nacon;

import com.atguigu.nacos.nacosconsumer.feign.PrividerFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: cly902
 * @date: Created in 2020年7月3日
 * @version: 1.0
 * @modified By:cly902
 */
@RestController
@RefreshScope
public class ConsumerController {

    @Autowired
    private PrividerFeign prividerFeign;
    @GetMapping("/hi")
    public String hi(HttpServletRequest request){
        String hello = prividerFeign.hello();
        return "----------> 十一------------>feign------>" + hello + request.getServerPort();
    }
}
