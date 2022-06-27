package juc.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; i++) {
                System.out.println("------:" + i);
            }
            System.out.println("t1线程2调用interrupt()后的中断标识：" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();

        System.out.println("t1线程默认的中断标识：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("t1线程1调用interrupt()后的中断标识：" + t1.isInterrupted());

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("t1线程3调用interrupt()后的中断标识：" + t1.isInterrupted());
    }
}
