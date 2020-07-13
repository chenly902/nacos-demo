package com.atguigu.nacos.nacosconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: cly902
 * @date: Created in 2020年7月3日
 * @version: 1.0
 * @modified By:cly902
 */
@FeignClient("nacos-provider")
public interface PrividerFeign {
    @GetMapping("/hello")
    public String hello();
}
