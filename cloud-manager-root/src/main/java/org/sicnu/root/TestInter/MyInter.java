package org.sicnu.root.TestInter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name 是服务提供方的应用名
@FeignClient(name = "eureka-client-yueleWeixin-shop")
public interface MyInter {
    // 调用的 Rest API，同时参数需要和远程的 Rest API 一样
    @GetMapping("/server/{id}")
    String getShopTest(@PathVariable("id")int id);
}
