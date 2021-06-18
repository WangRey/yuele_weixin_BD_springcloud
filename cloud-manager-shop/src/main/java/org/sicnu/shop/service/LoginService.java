package org.sicnu.shop.service;

import org.sicnu.shop.model.Userinfo;

public interface LoginService {
    Userinfo checkLogin(String phone, String password);
}
