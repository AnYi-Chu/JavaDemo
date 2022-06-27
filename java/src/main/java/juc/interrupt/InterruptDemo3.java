package juc.interrupt;

import java.util.concurrent.TimeUnit;

public class InterruptDemo3 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t 中断标识位：" + Thread.currentThread().isInterrupted() + "程序停止");
                    break;
                }

                try {
                    Thread.sleep(200);  //清除中断状态
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); //再次调用
                    e.printStackTrace();
                }
                System.out.println("------hello interruptDemo3");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            t1.interrupt();
        }, "t2").start();
    }
}
