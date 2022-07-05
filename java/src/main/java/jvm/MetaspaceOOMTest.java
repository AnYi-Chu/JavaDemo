package jvm;

import java.lang.reflect.Method;

/**
 * java.lang.OutOfMemoryError:Metaspace
 * <p>
 * java8后元空间代替永久代，它不使用虚拟机内存而使用本地内存，它存放虚拟机加载的类信息、常量池、静态变量、即时编译后的代码
 */
public class MetaspaceOOMTest {
    static class OOMTest {
    }

//    public static void main(String[] args) {
//        int i = 0;
//        try {
//            while (true) {
//                i++;
//                Enhancer enhancer = new Enhancer();
//                enhancer.setSuperclass(OOMTest.class);
//                enhancer.setUseCache(false);
//                enhancer.setCallback(new MethodInterceptor() {
//                    @Override
//                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                        return methodProxy.invokeSuper(o, args);
//                    }
//                });
//                enhancer.create();
//            }
//        } catch (Exception e) {
//            System.out.println("多少次后发生了异常:" +);
//            e.printStackTrace();
//        }
//    }
}
