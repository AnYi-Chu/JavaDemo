package juc.jmm;

/*
 * 一把锁：synchronized
 * 两个并：并发（concurrent）即一对多、
 *      并行（parallel）即多对多
 * 三个程：进程即拥有独立内存空间和系统资源的应用程序、
 *      线程即操作系统进行时序调度的基本单元，进程是线程的集合、
 *      管程（monitor）即监视器锁，每个对象天生自带一个对象监视器，每个被锁住的对象都会和monitor关联
 * */
public class ThreadBaseDemo {   //JUC概述
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        }, "t1");
        t1.start();

        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {

            }
        }, "t2").start();
    }
}
