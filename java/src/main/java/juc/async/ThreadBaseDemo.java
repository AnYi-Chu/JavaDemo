package juc.async;

public class ThreadBaseDemo {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
        }, "t1");
        t1.start();

        Object o = new Object();

        new Thread(() -> {
            synchronized (o) {

            }
        }, "t2").start();
    }
}
