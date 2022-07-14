package com.atguigu.spring.setinjection;

public class ClientSet {    //set注入解决循环依赖
    public static void main(String[] args) {
        ServiceAA a = new ServiceAA();
        ServiceBB b = new ServiceBB();
        a.setServiceBB(b);
        b.setServiceAA(a);
    }
}
