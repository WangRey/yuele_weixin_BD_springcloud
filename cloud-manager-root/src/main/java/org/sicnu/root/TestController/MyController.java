package org.sicnu.root.TestController;


import org.sicnu.root.TestInter.MyInter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.rmi.server.RemoteServer;

@RestController
public class MyController {
    @Resource
    private MyInter myInter;

    @GetMapping("/feign/{id}")
    public String feign(@PathVariable("id")int id) {
        String result = myInter.getShopTest(id);
        return result;
    }
}
