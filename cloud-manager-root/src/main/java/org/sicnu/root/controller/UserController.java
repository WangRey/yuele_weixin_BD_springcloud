package org.sicnu.root.controller;

import org.sicnu.root.model.User;
import org.sicnu.root.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @CrossOrigin
    @PostMapping(value = "/create_user")
    public Map<String ,Object> createUser(@RequestParam(value = "userName",required = true) String userName, @RequestParam(value = "password",required = true) String password, @RequestParam(value = "fullName",required = false) String fullName){
        Map<String ,Object> result = new HashMap<String ,Object>();
        User user = new User();
        user.setUserName(userName);
        //加密xx
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        user.setPassword(md5Password);
        user.setFullName(fullName);
        userRepo.save(user);
        result.put("status","ok");
        result.put("userId",user.getId());
        return result;
    }

    //登录接口
    @CrossOrigin
    @PostMapping(value = "/login")
    public Map<String ,Object> login(@RequestParam(value = "userName",required = true) String userName, @RequestParam(value = "password",required = true) String password){
        Map<String , Object> result=new HashMap<String ,Object>();
        List<User> userList = userRepo.findByUserName(userName);
        if(userList.size()==0){
            result.put("status","username or password error");
            return result;
        }
        User user = userList.get(0);
        String oldPassword = user.getPassword();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(md5Password.equals(oldPassword)){
            result.put("status","ok");
            return result;
        }else {
            result.put("status","username or password error");
            return result;
        }


    }
}
