package juc.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 获取写锁--->获取读锁--->释放写锁，写锁降级成为读锁，不支持锁升级
 * 如果一个线程占有写锁，在未释放写锁前，能占有读锁，实现锁降级
 * 读锁未释放前，写锁无法上锁
 * */
public class LockDownGradingDemo {  //锁降级
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

//        readLock.lock();
//        System.out.println("------读取");
//        readLock.unlock();

//        writeLock.lock();
//        System.out.println("------写入");
//        writeLock.unlock();

//        writeLock.lock();
//        System.out.println("------写入");
//        readLock.lock();
//        writeLock.unlock();
//        readLock.unlock();

        readLock.lock();
        System.out.println("------读取");
        writeLock.lock();
        System.out.println("------写入");
        writeLock.unlock();
        readLock.unlock();
    }
}
