package juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            LockSupport.park(); //必须与unpark()配对，无锁块要求
            System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            LockSupport.unpark(t1); //必须与park()配对，只能使用一次
            System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
        }, "t2").start();
    }

    private static void lockAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ------come in");
                condition.await();  //必须在lock内，且在signal()前执行
                System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal(); //必须在lock内，且在await()后执行
                System.out.println(Thread.currentThread().getName() + "\t ------发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();
    }

    private static void sycnWaitNotify() {
        Object objectLock = new Object();

        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t ------come in");
                try {
                    objectLock.wait();  //必须在synchronized内，且在notify()前执行
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ------被唤醒");
            }
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();    //必须在synchronized内，且在wait()后执行
                System.out.println(Thread.currentThread().getName() + "\t ------发出通知");
            }
        }, "t2").start();
    }
}
