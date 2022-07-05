package juc.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyData {
    ThreadLocal<Integer> threadLocalField = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocalField.set(1 + threadLocalField.get());
    }
}

public class ThreadLocalDemo2 { //在线程池中由于线程复用导致的内存泄漏
    public static void main(String[] args) {
        MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myData.threadLocalField.get();
                        myData.add();
                        Integer afterInt = myData.threadLocalField.get();
                        System.out.println(Thread.currentThread().getName() + "\t" + "beforeInt：" + beforeInt + "\t" + "afterInt：" + afterInt);
                    } finally {
                        myData.threadLocalField.remove();   //防止内存泄露
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
