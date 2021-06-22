package org.sicnu.zuul.config;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.sicnu.zuul.exception.DefException;
import org.sicnu.zuul.exception.ExceptionType;
import org.sicnu.zuul.feignService.GetUserinfo;
import org.sicnu.zuul.model.AjaxResponse;
import org.sicnu.zuul.model.Userinfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class GlobalFilter  extends ZuulFilter {

    @Resource
    GetUserinfo getUserinfo;

    private static final Logger logger = LoggerFactory.getLogger(GlobalFilter.class);

    private static final String LOGIN_REQUEST = "login";

    private static final String REGISTER_REQUEST = "add";

    /**
     * filterType：返回一个字符串代表过滤器的类型，在zuul中定义了四种不同生命周期的过滤器类型，具体如下：
     * pre：路由之前
     * routing：路由之时
     * post： 路由之后
     * error：发送错误调用
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filterOrder：过滤的顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }

    /**
     * shouldFilter：这里可以写逻辑判断，是否要过滤，本文true,永远过滤。
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * run：过滤器的具体逻辑。可用很复杂，包括查sql，nosql去判断该请求到底有没有权限访问。
     * @return
     * @throws ZuulException
     */

    @Override
    public Object run() throws ZuulException {
        // 获取请求上下文
        RequestContext rc = RequestContext.getCurrentContext();
        HttpServletRequest request = rc.getRequest();
        // 响应类型
        rc.getResponse().setContentType("application/json;charset=utf-8");
        //是登录那没事了
        if (request.getServletPath().endsWith(LOGIN_REQUEST) ||request.getServletPath().endsWith(REGISTER_REQUEST)  ) {
            return null;
        }
        //test
        if (request.getServletPath().endsWith("log")) {
            return null;
        }
        String token = request.getHeader("token");
        if (token == null) {
            log.info("token为空");
            try {
                // 请求结束，不在继续向下请求。
                rc.setSendZuulResponse(false);
                // 响应状态码，HTTP 401 错误代表用户没有访问权限
                rc.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                rc.getResponse().getWriter().write(JSONObject.toJSONString(AjaxResponse.error("验证信息消失/验证信息有误/不存在该用户/输入信息有误")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        // 获取token中的phone
        String phone;
        try {
            log.info(JWT.decode(token).getAudience().toString());
            phone = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            log.info("提取token中的信息失败");
            try {
                // 请求结束，不在继续向下请求。
                rc.setSendZuulResponse(false);
                // 响应状态码，HTTP 401 错误代表用户没有访问权限
                rc.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                rc.getResponse().getWriter().write(JSONObject.toJSONString(AjaxResponse.error("无法提取信息/无效的验证信息/不存在该用户/输入信息有误")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        log.info(phone);

        // 根据userId取用户
        Map<String,String> mp=new HashedMap();
        mp.put("phone",phone);
        Userinfo user = getUserinfo.userinfo(mp);
        if (user == null) {
            log.info("token中的信息找不到用户");
            try {
                // 请求结束，不在继续向下请求。
                rc.setSendZuulResponse(false);
                // 响应状态码，HTTP 401 错误代表用户没有访问权限
                rc.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                rc.getResponse().getWriter().write(JSONObject.toJSONString(AjaxResponse.error("无效的验证信息/不存在该用户/输入信息有误")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        JWTVerifier jwtVerify = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            // 验证token
            jwtVerify.verify(token);
        } catch (JWTVerificationException e) {
            log.info("token验证不通过");
            try {
                // 请求结束，不在继续向下请求。
                rc.setSendZuulResponse(false);
                // 响应状态码，HTTP 401 错误代表用户没有访问权限
                rc.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
                rc.getResponse().getWriter().write(JSONObject.toJSONString(AjaxResponse.error("/无效的验证信息/不存在该用户/输入信息有误")));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return null;
        }

        return null;
    }

    /**
     * 判断Token是否合法
     * @param token token
     * @return  是否合法
     */
    private boolean isInvalidToken(String token) {
        return true;
    }

    /**
     * 根据token获得username
     *
     * @param token token
     * @return  结果集
     */
    private String getUsernameByToken(String token) {
        return null;
    }
}
