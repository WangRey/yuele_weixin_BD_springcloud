package org.sicnu.shop.feignService;

import org.sicnu.shop.dao.UserRepository;
import org.sicnu.shop.model.Userinfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/feign")
public class GetUserinfo {
    @Resource
    UserRepository userRepository;

    @RequestMapping("/userinfo")
    public Userinfo Userinfo(@RequestBody Map<String,String> m){
       return userRepository.findUserByPhone(m.get("phone"));
    }


}
