package org.sicnu.shop.service.imp;

import org.sicnu.shop.dao.UserRepository;
import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.service.ShoppingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ShoppingServiceImp implements ShoppingService {

    @Resource
    UserRepository userRepository;

    @Override
    public void buy(String amount,String phone) {
        Userinfo userByPhone = userRepository.findUserByPhone(phone);
//        System.out.println(Double.parseDouble(userByPhone.getBalance()));
//        System.out.println(Double.parseDouble(amount));
        Double d = Double.parseDouble(userByPhone.getBalance())-Double.parseDouble(amount);
        userByPhone.setBalance(d.toString());
        userRepository.save(userByPhone);
    }
}
