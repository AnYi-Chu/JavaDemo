package juc.jmm;

/*
 * DCL(Double Check Lock双端检锁机制)不一定线程安全，原因是有指令重排序存在
 * 即某一线程在执行第一次检测时，它读取到的instance不为null时，instance的引用对象可能没有完成初始化
 * 分配对象内存空间->初始化对象->设置对象指向内存地址，这些不存在数据依赖关系，其结果与是否重排序无关，即重排序是允许存在的
 * 指令重排序只保证串行语义的执行的一致性，不管多线程间的语义一致性，即存在线程安全问题
 */
public class SafeDoubleCheckSingleton { //双端检索机制
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

