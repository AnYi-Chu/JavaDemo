package juc.tools;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch是做减法
 * 让一些线程阻塞，直到另一线程完成一系列操作后才被唤醒
 * 它主要有两个方法countDown()和await()
 * 当一个或多个线程调用时，调用线程会被阻塞
 * 当其他线程调用countDown()时，会将计数器减一（它不会阻塞线程），当计数器为零时，因调用await()，被阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 国，被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 一统天下");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getReCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }

    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 上完自习，离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t 班长关门");
    }
}
