package com.atguigu.spring.setinjection;

import org.springframework.stereotype.Component;

@Component
public class ServiceAA {
    private ServiceBB serviceBB;

    public void setServiceBB(ServiceBB serviceBB) {
        this.serviceBB = serviceBB;
    }
}
