package org.sicnu.shop.controller;


import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.model.VOmodel.UserInfoVO;
import org.sicnu.shop.service.LoginService;
import org.sicnu.shop.utils.jwtUtils.JWTHelper;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    LoginService loginService;

    @RequestMapping("/login")
    public AjaxResponse userLogin(@RequestBody Map<String ,String> message){
        String md5Password = DigestUtils.md5DigestAsHex(message.get("password").getBytes());
        Userinfo userinfo = loginService.checkLogin(message.get("phone"), md5Password);//返加密

        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        UserInfoVO userInfoVO = mapper.map(userinfo, UserInfoVO.class);


        Map<String,Object> result = new HashMap<String,Object>();
        result.put("userinfo",userInfoVO);
        result.put("token", JWTHelper.createJWTToken( userinfo ));

        return AjaxResponse.success(result);
    }
    @RequestMapping("/loginOut")
    public AjaxResponse loginOut(){
        return AjaxResponse.success();
    }

    @RequestMapping("/test")
    public AjaxResponse test(){
        Map<String ,String> mp = new HashMap<String ,String>();
        mp.put("123","456");
        return AjaxResponse.success(mp);
    }
}
