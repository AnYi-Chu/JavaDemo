package com.atguigu.spring.constructorinjection;

import org.springframework.stereotype.Component;

@Component
public class ServiceA {
    private ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
