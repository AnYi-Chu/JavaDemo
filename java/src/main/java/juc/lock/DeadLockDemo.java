package juc.lock;

import java.util.concurrent.TimeUnit;

/*死锁排查
 * 1.使用命令jps -l找到进程对应的编号，使用命令jstack 进程编号查看
 * 2.运行命令jconsole查看*/
public class DeadLockDemo {
    public static void main(String[] args) {
        final Object objectA = new Object();
        final Object objectB = new Object();

        new Thread(() -> {
            synchronized (objectA) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有a，尝试获得b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectB) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功获得b");
                }
            }
        }, "A").start();

        new Thread(() -> {
            synchronized (objectB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有b，尝试获得a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (objectA) {
                    System.out.println(Thread.currentThread().getName() + "\t 成功获得a");
                }
            }
        }, "B").start();
    }
}
