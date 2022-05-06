package spring.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect //生成代理对象
@Order(1)   //执行优先级
public class UserProxy {

    //切入点
    @Pointcut("execution(* spring.aop.annotation.User.add(..))")
    public void pointcut() {

    }

    //前置通知
    @Before("pointcut()")
    public void before() {
        System.out.println("before...");
    }

    //后置通知
    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("afterReturning...");
    }

    //环绕通知
    @Around("pointcut()")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before...");
        proceedingJoinPoint.proceed();
        System.out.println("around after...");
    }

    //异常通知
    @AfterThrowing("pointcut()")
    public void afterThrowing() {
        System.out.println("afterThrowing...");
    }

    //最终通知
    @After("pointcut()")
    public void after() {
        System.out.println("after...");
    }
}
