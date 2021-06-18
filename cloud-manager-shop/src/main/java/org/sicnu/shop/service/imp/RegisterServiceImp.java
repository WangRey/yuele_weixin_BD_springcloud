package org.sicnu.shop.service.imp;

import lombok.extern.slf4j.Slf4j;
import org.sicnu.shop.dao.UserRepository;
import org.sicnu.shop.exception.DefException;
import org.sicnu.shop.exception.ExceptionType;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.model.VOmodel.UserInfoVO;
import org.sicnu.shop.service.RegisterService;
import org.springframework.stereotype.Service;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import javax.annotation.Resource;

@Service
@Slf4j
public class RegisterServiceImp implements RegisterService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserInfoVO registerUserService(Userinfo user){
        userRepository.save(user);
        Mapper mapper = DozerBeanMapperBuilder.buildDefault();
        UserInfoVO userInfoVO = mapper.map(user, UserInfoVO.class);
        return userInfoVO;
    }

    @Override
    public void checkBaseInfoFormat(Userinfo user) {
        if(user.getPassword().length()<8)
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"密码长度不够八位");
        if(user.getIdCard().length()!=18&&user.getIdCard().length()!=0)
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"身份证信息有误");
        if(!user.getNickName().matches("[\\w]+"))
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"昵称格式有误");
        if(!user.getEmail().matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+"))
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"邮箱格式错误");
        Userinfo User_phone = userRepository.findUserByPhone(user.getPhone());
        if(User_phone!=null)
            throw new DefException(ExceptionType.USER_INPUT_ERROR,"改手机号已经被注册");
    }

}
