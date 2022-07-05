package juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * CAS是cpu的原子指令即（Atomic::cmpxchg(x,addr,e)）==e，即比较当前工作内存中的值和主内存中的值，如果相同执行规定操作，否则继续比较直到主内存和工作内存的值相同为止，
 * Unsafe是CAS的核心类，存在与sun.mise包中，其内部方法操作可以像C的指针一样直接操作内存，其所有方法都是native修饰，即这些方法都直接调用操作系统底层资源执行相应任务
 * CAS有三个操作数：内存值V，旧的预期值A，要修改的更新值B，当且仅当A与V相同时，V修改为B，否则什么都不做
 * CAS和synchronized的区别：
 *      synchronized是加锁，同一时间只跑一个；加锁可以保证多个变量原子性
 *      CAS是用do...while，允许多线程并发修改，互相比较，直到完成所有线程；它只保证var1原子性
 * */
public class CASDemo {  //比较并交换
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2022) + "\t" + atomicInteger.get());
    }
}
