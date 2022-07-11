package juc.aqs;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * AQS是抽象的队列同步器，分为一个抽象的先进先出的双向队列和一个int变量表示持有锁的状态
 * 排队等候机制：将请求共享资源的线程及自身的等待状态封装成队列的节点对象（Node），通过CAS、自选以及LockSupport.park()的方式，维护state变量的状态，使并发达到同步的效果
 * 工作原理：通过自旋等待，通过state变量判断是否阻塞，从尾部入队从头部出队
 * */
public class AQSDemo {
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
//        Lock lock = new ReentrantLock(true);
//        lock.lock();
//        lock.unlock();
//
//        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//        readWriteLock.readLock().lock();
//        readWriteLock.readLock().unlock();
//        readWriteLock.writeLock().lock();
//        readWriteLock.writeLock().unlock();
//
//        CountDownLatch countDownLatch = new CountDownLatch(10);
//        countDownLatch.countDown();
//        countDownLatch.await();
//
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
//        cyclicBarrier.await();
//
//        Semaphore semaphore = new Semaphore(10);
//        semaphore.acquire();
//        semaphore.release();
//
//        LockSupport.park();
//        LockSupport.unpark();

        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("------come in A");
                try {
                    TimeUnit.MINUTES.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                reentrantLock.unlock();
            }
        }, "A").start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("------come in B");
            } finally {
                reentrantLock.unlock();
            }
        }, "B").start();

        new Thread(() -> {
            reentrantLock.lock();
            try {
                System.out.println("------come in C");
            } finally {
                reentrantLock.unlock();
            }
        }, "C").start();
    }
}
