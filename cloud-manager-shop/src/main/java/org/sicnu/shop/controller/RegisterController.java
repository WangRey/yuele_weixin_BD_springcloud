package org.sicnu.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.model.VOmodel.UserInfoVO;
import org.sicnu.shop.service.RegisterService;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/user")
public class RegisterController {

    @Resource
    RegisterService registerService;

    @PostMapping("/add")
    public AjaxResponse add(@RequestBody Userinfo user){
        registerService.checkBaseInfoFormat(user);
        String md5Password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        user.setPassword(md5Password);
        UserInfoVO userInfoVO = registerService.registerUserService(user);
        return AjaxResponse.success(userInfoVO);
    }



}
