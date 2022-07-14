package com.atguigu.spring.constructorinjection;

/*
 * spring解决循环依赖依靠的是bean的中间态这个概念，即已经实例化但还没初始化的状态
 * 实例化的过程是通过构造器创建，如果对象没有创建好是无法提前曝光的
 * */
public class ClientConstructor {    //构造注入无法解决循环依赖问题
    public static void main(String[] args) {
        new ServiceA(new ServiceB(new ServiceA(null)));
    }
}
