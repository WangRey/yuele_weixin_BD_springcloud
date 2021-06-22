package org.sicnu.shop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//* 表示全部拦截
@Slf4j
@Component
@WebFilter(filterName = "MyFilter",urlPatterns = "/*")
public class CorsFilterConfig implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String origin = httpServletRequest.getHeader("Origin");
        log.info("filter中请求源是:"+origin);
        if(origin == null) {
            origin = httpServletRequest.getHeader("Referer");
        }
        log.info("filter中请求源是:"+origin);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        //指定允许其他域名访问,谁访问谁允许
//        httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("origin"));

//        httpServletResponse.setHeader("Access-Control-Allow-Origin", "http://localhost:10086");

        //响应头设置
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");

        //允许携带cookie
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        //响应类型
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");

        //option验证后时间
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
