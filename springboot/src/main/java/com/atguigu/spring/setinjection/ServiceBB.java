package com.atguigu.spring.setinjection;

import org.springframework.stereotype.Component;

@Component
public class ServiceBB {
    private ServiceAA serviceAA;

    public void setServiceAA(ServiceAA serviceAA) {
        this.serviceAA = serviceAA;
    }
}
