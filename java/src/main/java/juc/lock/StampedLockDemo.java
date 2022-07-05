package juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t" + "写线程准备修改");
        try {
            number = number + 13;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "写线程结束修改");
    }

    public void read() {    //悲观读，读未完成时写锁无法获得锁
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t" + "读线程准备修改");
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取中");
        }
        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t" + "result:" + result);
            System.out.println("写线程失败，读写互斥");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }


    public void tryOptimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        System.out.println("4秒前stampedLock.validate的方法值" + "\t" + stampedLock.validate(stamp)); //ture无修改，false有修改
        for (int i = 0; i < 4; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取" + i + "秒" + "后stampedLock.validate的方法值" + "\t" + stampedLock.validate(stamp));
        }
        if (!stampedLock.validate(stamp)) {
            System.out.println("有人修改过------有写操作");
            stamp = stampedLock.readLock();
            try {
                System.out.println("从乐观读升级为悲观读");
                result = number;
                System.out.println("悲观读后result：" + result);
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "finally value:" + result);
    }

    public static void main(String[] args) {
        StampedLockDemo resource = new StampedLockDemo();

//        new Thread(() -> {
//            resource.read();
//        }, "readThread").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(1);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        new Thread(() -> {
//            System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
//            resource.write();
//        }, "writeThread").start();
//
//        try {
//            TimeUnit.SECONDS.sleep(4);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(Thread.currentThread().getName() + "\t" + "number:" + number);

        new Thread(() -> {
            resource.tryOptimisticRead();
        }, "readThread").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "------come in");
            resource.write();
        }, "writeThread").start();
    }
}
