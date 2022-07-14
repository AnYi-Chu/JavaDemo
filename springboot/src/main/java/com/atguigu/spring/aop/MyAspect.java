package com.atguigu.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @Before("execution(public int com.atguigu.spring.aop.CalcServiceImpl.*(..))")
    public void beforeNotify() {
        System.out.println("@Before前置通知");
    }

    @After("execution(public int com.atguigu.spring.aop.CalcServiceImpl.*(..))")
    public void afterNotify() {
        System.out.println("@After后置通知");
    }

    @AfterReturning("execution(public int com.atguigu.spring.aop.CalcServiceImpl.*(..))")
    public void afterReturningNotify() {
        System.out.println("@AfterReturning返回通知");
    }

    @AfterThrowing("execution(public int com.atguigu.spring.aop.CalcServiceImpl.*(..))")
    public void afterThrowingNotify() {
        System.out.println("@AfterThrowing异常通知");
    }

    @Around("execution(public int com.atguigu.spring.aop.CalcServiceImpl.*(..))")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object retValue = null;
        System.out.println("环绕通知前");
        retValue = proceedingJoinPoint.proceed();
        System.out.println("环绕通知后");
        return retValue;
    }
}
