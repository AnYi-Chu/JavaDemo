package juc.lockSupport;

import java.util.concurrent.TimeUnit;

/*
 * interrupt()设置正在运行的线程中断状态为true，线程不会阻塞，对不活动的线程不产生任何影响
 * */
public class InterruptDemo2 {   //线程中断
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
        System.out.println("t1线程3调用interrupt()后的中断标识：" + t1.isInterrupted());   //interrupt()对不活动的线程不产生任何影响
    }
}
