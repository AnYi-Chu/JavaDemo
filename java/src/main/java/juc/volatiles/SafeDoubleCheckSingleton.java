package juc.volatiles;

public class SafeDoubleCheckSingleton {
    private volatile static SafeDoubleCheckSingleton singleton; //volatile实现线程安全的延迟初始化

    private SafeDoubleCheckSingleton() {
    }

    public static SafeDoubleCheckSingleton getInstance() {  //双重锁设计
        if (singleton == null) {
            synchronized (SafeDoubleCheckSingleton.class) {
                if (singleton == null) {
                    singleton = new SafeDoubleCheckSingleton(); //并发环境下由于重排序导致该对象在未完成初始化就被其他线程读取
                }
            }
        }
        return singleton;
    }
}

