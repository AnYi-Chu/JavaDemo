package spring.aop.agent;

import java.lang.reflect.Proxy;

public class JDKProxy {
    public static void main(String[] args) {
        //创建接口代理对象
        Class[] interfaces = {UserDao.class};
        //newProxyInstance参数：类加载器，增强方法所在的类实现的接口（多个接口用，分割），实现InvocationHandler接口创建代理对象的增强方法
//        Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                return null;
//            }
//        });
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        UserDao userDao = (UserDao) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), interfaces, new UserDaoProxy(userDaoImpl));
        int add = userDao.add(1, 2);
        System.out.println(add);
    }
}
