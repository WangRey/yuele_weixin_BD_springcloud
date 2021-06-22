package org.sicnu.shop.utilJwt;



import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.sicnu.shop.model.Userinfo;

public class JWTHelper {

    // 开发环境设置过期时间
    public long EXPIRE_TIME = 2*60*1000;

    public static String createJWTToken(Userinfo user){
        String token = JWT.create().withAudience(user.getPhone()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
    /**
     * 通过header头部的token得到login_name
     * @return token中包含的用户名
     */
    public static String getPhone(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            System.out.println("jwt解析的phone = " + jwt.getClaim("user_name").asString());
            return jwt.getClaim("user_name").asString();
        } catch (JWTDecodeException e) {
            System.out.println("解析name失败");
            return null;
        }
    }
}
