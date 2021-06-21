package org.sicnu.shop.controller;

import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.model.VOmodel.UserInfoVO;
import org.sicnu.shop.utilRedis.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class TestController {
    @Resource
    RedisUtil redisUtil;

    @GetMapping("/server/{id}")
    @ResponseBody
    public AjaxResponse server(@PathVariable("id") int id) {
        return AjaxResponse.success();
    }

    @PostMapping("/testRedis/{id}")
    @ResponseBody
    public AjaxResponse redistest(@PathVariable("id") int id) {
        UserInfoVO u  = new UserInfoVO();
        u.setEmail("546789");
        u.setNickName("AA");
        u.setRealName("BB");
        redisUtil.set("wr",u);
        System.out.println("已经存入redis");
        UserInfoVO uu = (UserInfoVO)redisUtil.get("wr");
        System.out.println(uu.getNickName() +  " "+uu.getRealName() +  " "+uu.getEmail());
        return AjaxResponse.success();

    }
}
