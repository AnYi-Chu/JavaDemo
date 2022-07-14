package com.atguigu.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * 一级缓存为单例池（singletonObjects），二级缓存为提前曝光对象（earlySingletonObjects），三级缓存为提前曝光对象工厂（singletonFactories）
 *
 * 1.调用doGetBean()方法，想要获取beanA，于是调用getSingleton()方法从缓存中查找beanA
 * 2.在getSingleton()方法中，从一级缓存中查找，没有，返回null
 * 3.doGetBean()方法中获取到的bean为null，于是走对应的处理逻辑，调用getSingleton()的重载方法（参数为ObjectFactory）
 * 4.在geSingleton()方法中，先将beanA_name添加到一个集合中，用于标记该bean正在创建中。然后回调匿名内部类的creatBean方法
 * 5.进入AbstractAutowireCapableBeanFactory#doCreateBean，先反射调用构造器创建出beanA的实例，然后判断是否为单例、是否允许提前暴露引用（对单例一般为true）、是否正在创建中（即是否在第四步的集合中）。判断为true则将beanA添加到三级缓存中
 * 6.对beanA进行属性填充，此时检测到beanA依赖于beanB，于是开始查找beanB
 * 7.调用doGetBean()方法，和上面beanA的过程一样，到缓存中查找beanB，没有则创建，然后给beanB填充属性
 * 8.此时beanB依赖于beanA，调用getSingleton()获取beanA，依次从以及、二级、三级缓存中找，此时从三级缓存中获取到beanA的创建工厂，通过创建工厂获取到singletonObject，此时这个singletonObject指向的就是上面在doCreateBean()方法中实例化的beanA
 * 9.这样beanB就获取到了beanA的依赖，于是beanB顺利完成实例化，并将breanA从三级缓存移动到二级缓存中
 * 10.随后beanA继续他的属性填充，此时也获取到了beanB，beanA也随之完成了创建，回到getSingleton()方法中继续向下执行，将beanA从二级缓存移动到一级缓存中
 * */
public class ClientSpringContainer {    //三级缓存
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        A a = context.getBean("a", A.class);
        B b = context.getBean("b", B.class);
    }
}
