package aop.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class UserDaoProxy implements InvocationHandler {
    private Object obj;

    public UserDaoProxy(Object obj) {
        this.obj = obj;
    }

    //增强的逻辑
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //方法执行前
        System.out.println("before...");
        //方法执行
        Object invoke = method.invoke(obj, args);
        //方法执行后
        System.out.println("after...");
        return invoke;
    }
}
