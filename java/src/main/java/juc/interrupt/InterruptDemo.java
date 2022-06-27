package juc.interrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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

    private static void m2_atomicBoolean() {
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

    private static void m1_volatile() {
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
