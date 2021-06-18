package org.sicnu.shop.utils.jwtUtils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.sicnu.shop.model.Userinfo;

public class JWTHelper {
    public static String createJWTToken(Userinfo user){
        String token = JWT.create().withAudience(user.getPhone()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
