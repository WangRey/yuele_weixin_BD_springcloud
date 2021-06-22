package org.sicnu.shop.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.sicnu.shop.model.AjaxResponse;
import org.sicnu.shop.service.imp.SysLogServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Aspect
@Repository
public class LogAspect {

    @Autowired
    private HttpServletRequest request;


    @Pointcut("execution(public * org.sicnu.shop.controller..*(..))")
    public void successPointcut(){}

    @Pointcut("execution(public * org.sicnu.shop.exception..*(..))")
    public void errorPointcut(){}

    @Resource
    SysLogServiceImp sysLogServiceImp;

    @AfterReturning(value = "successPointcut()", returning = "rvt")
    public void successPointcutLog(JoinPoint joinPoint,Object rvt) throws Throwable {
        AjaxResponse aj = (AjaxResponse)rvt;
        sysLogServiceImp.addLog(request,aj);

    }

    @AfterReturning(value = "errorPointcut()", returning = "rvt")
    public void errorPointcutLog(JoinPoint joinPoint, Object rvt) throws Throwable {
        AjaxResponse aj = (AjaxResponse)rvt;
        sysLogServiceImp.addLog(request,aj);
    }

    private static HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }
}
