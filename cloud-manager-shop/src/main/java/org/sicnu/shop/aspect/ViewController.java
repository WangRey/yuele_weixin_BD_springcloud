package org.sicnu.shop.aspect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/log")
    public String log(){
        return "log.html";
    }
}
