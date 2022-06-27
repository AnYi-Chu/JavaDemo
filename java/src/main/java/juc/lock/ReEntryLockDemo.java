package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReEntryLockDemo {  //重入锁
    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t ------come in m1");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t ------end in m1");
    }

    public synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ------come in m2");
        m3();
    }

    public synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ------come in m3");
    }

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();
//        new Thread(() -> {
//            reEntryLockDemo.m1();
//        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ------come in外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ------come in内层调用");
                } finally {
                    lock.unlock();
                }
            } finally {
                //lock.unlock();  //加锁解锁次数必须一致，否则会阻塞
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ------come in外层调用");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "\t ------come in内层调用");
                } finally {
                    lock.unlock();
                }
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void reEntryM1() {
        final Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "\t ------外层调用");
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + "\t ------中层调用");
                    synchronized (object) {
                        System.out.println(Thread.currentThread().getName() + "\t ------内层调用");
                    }
                }
            }
        }, "t1").start();
    }
}
