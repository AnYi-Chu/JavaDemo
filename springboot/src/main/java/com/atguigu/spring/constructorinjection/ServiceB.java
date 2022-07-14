package com.atguigu.spring.constructorinjection;

import com.atguigu.spring.constructorinjection.ServiceA;
import org.springframework.stereotype.Component;

@Component
public class ServiceB {
    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
