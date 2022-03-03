package com.example.petproject.aspects;

import com.example.petproject.services.VKService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Autowired
    VKService vkService;

    @Pointcut("execution(* com.example.petproject.controllers.*.*(..)))")
    private void anyControllerMethod(){}
    @Pointcut("execution(* com.example.petproject.controllers.MainPage.mainPage(..))")
    private void mainPageMethod(){}
    @Pointcut("execution(* com.example.petproject.controllers.MainPage.login(..))")
    private void loginMethod(){}
    @Pointcut("anyControllerMethod() && !mainPageMethod() && !loginMethod()")
    private void anyControllerMethodExceptMainPageAndLoginMethod(){}


    @Around("anyControllerMethodExceptMainPageAndLoginMethod()")
    public Object returnToLoginIfNot(ProceedingJoinPoint point) throws Throwable{
        if (!vkService.isLogin())
            return "redirect:/";

        return point.proceed();
    }

}
