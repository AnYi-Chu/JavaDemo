package juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 可重入锁：可重复递归调用的锁，在外层使用锁后，在内层仍然可以使用，并且不会发生死锁
 * synchronized是隐式锁，即在synchronized方法或代码块内部调用被雷的其他synchronized修饰的方法或代码块时，是永远可以得到锁
 *      每个锁对象拥有一个锁计数器和一个指向持有该锁的线程的指针，在执行monitorenter时会将指针指向当前线程并将计数器加1，在执行monitorexit时会将计数器减1，计数器为0即锁已被释放
 * ReentrantLock是显式锁，加锁解锁次数必须一致，否则会阻塞
 * */
public class ReEntryLockDemo {  //重入锁
    public synchronized void m1() { //同步方法
        System.out.println(Thread.currentThread().getName() + "\t ------come in m1");
        m2();
    }

    public synchronized void m2() { //同步方法
        System.out.println(Thread.currentThread().getName() + "\t ------come in m2");
        m3();
    }

    public synchronized void m3() { //同步方法
        System.out.println(Thread.currentThread().getName() + "\t ------come in m3");
    }

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReEntryLockDemo reEntryLockDemo = new ReEntryLockDemo();

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

    private static void reEntryM1() {   //同步代码块
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
