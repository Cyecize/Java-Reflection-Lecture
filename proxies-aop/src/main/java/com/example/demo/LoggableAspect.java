package com.example.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
@EnableAspectJAutoProxy
public class LoggableAspect {
    @Around("execution(* (@com.example.demo.Loggable *).*(..))")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("LoggableAspect is LOGGING");
        return proceedingJoinPoint.proceed();
    }
}

