package org.sicnu.shop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/server/{id}")
    public String server(@PathVariable("id") int id) {
        return "收到请求Id : " + id + "，结束";
    }
}
