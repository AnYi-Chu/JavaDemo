package juc.lockSupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 * interrupt()设置正在运行的线程中断状态为true，线程不会阻塞，对不活动的线程不产生任何影响；对阻塞线程使用interrupt()会清除中断标志，立刻退出阻塞状态，并抛出InterruptedException异常
 * interrupted()判断线程中断状态，并清除中断状态将中断状态为false
 * isInterrupted()判断线程中断状态
 * */
public class InterruptDemo {    //线程中断
    static volatile boolean isStop = false;
    static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {   //判断中断标志
                    System.out.println(Thread.currentThread().getName() + "\t interrupted被修改为true，程序停止");
                    break;
                }
                System.out.println("t1------hello interrupted");
            }
        }, "t1");
        t1.start();

//        System.out.println("t1------默认中断标志位：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            t1.interrupt(); //设置中断标志
        }, "t2").start();
    }

    private static void m2_atomicBoolean() {    //通过AtomicBoolean实现线程中断
        new Thread(() -> {
            while (true) {
                if (atomicBoolean.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t atomicBoolean被修改为true，程序停止");
                    break;
                }
                System.out.println("t1------hello atomicBoolean");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            atomicBoolean.set(true);
        }, "t2").start();
    }

    private static void m1_volatile() { //通过volatile变量实现线程中断
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，程序停止");
                    break;
                }
                System.out.println("t1------hello volatile");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }
}
