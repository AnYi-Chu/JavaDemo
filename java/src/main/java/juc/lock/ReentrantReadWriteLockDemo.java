package juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyResource {
    Map<String, String> map = new HashMap<>();
    Lock lock = new ReentrantLock();
    ReadWriteLock rwLock = new ReentrantReadWriteLock();    //读写互斥，读读共享

    public void write(String key, String value) {
//        lock.lock();
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在写入");
            map.put(key, value);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "写入完成");
        } finally {
//            lock.unlock();
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
//        lock.lock();
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
            String result = map.get(key);
            try {
                TimeUnit.MILLISECONDS.sleep(2000);  //读锁没有完成前，写锁无法获得
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "读取完成");
        } finally {
//            lock.unlock();
            rwLock.readLock().unlock();
        }
    }
}

/*
 * 锁饥饿：读锁多写锁少，写锁抢不到
 * 读写锁：读写互斥，读读共享，读锁没有完成前，写锁无法获得
 * */
public class ReentrantReadWriteLockDemo {   //读写锁
    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.read(finalI + "");
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(1);  //读锁没有完成前，写锁无法获得
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI + "", finalI + "");
            }, "新" + String.valueOf(i)).start();
        }
    }
}
