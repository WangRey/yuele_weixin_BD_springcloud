package org.sicnu.shop.controller;

import org.sicnu.shop.model.AjaxResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/server/{id}")
    @ResponseBody
    public AjaxResponse server(@PathVariable("id") int id) {
        return AjaxResponse.success();
    }
}
