package org.sicnu.shop.service;

import org.sicnu.shop.model.Userinfo;
import org.sicnu.shop.model.VOmodel.UserInfoVO;
public interface RegisterService {

    UserInfoVO registerUserService(Userinfo u);
    void checkBaseInfoFormat(Userinfo u);
}
