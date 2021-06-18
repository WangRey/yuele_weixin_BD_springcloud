package org.sicnu.shop.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.sicnu.shop.dao.UserRepository;
import org.sicnu.shop.exception.DefException;
import org.sicnu.shop.exception.ExceptionType;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class LoginServiceLmp implements LoginService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Userinfo checkLogin(String phone, String password) {
        Userinfo user = userRepository.findUserByPhone(phone);
        if(user==null)
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"该手机号码还未注册/输入有误");
        if(user.getPassword().equals(password)){
            return user;
        } else
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"密码错误/输入有误");
    }
}
