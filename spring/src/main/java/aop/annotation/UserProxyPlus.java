package aop.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class UserProxyPlus {

    @Before("execution(* aop.annotation.User.add(..))")
    public void before() {
        System.out.println("之前...");
    }
}
