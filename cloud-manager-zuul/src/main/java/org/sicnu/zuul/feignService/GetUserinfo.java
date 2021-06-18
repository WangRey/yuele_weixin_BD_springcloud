package org.sicnu.zuul.feignService;

import org.sicnu.zuul.model.Userinfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

// name 是服务提供方的应用名
@FeignClient(name = "eureka-client-yueleWeixin-shop")
public interface GetUserinfo {
    // 调用的 Rest API，同时参数需要和远程的 Rest API 一样
    @GetMapping("/feign/userinfo")
    Userinfo userinfo(Map<String,String> m);
}
