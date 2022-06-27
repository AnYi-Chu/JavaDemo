package juc.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class MyData {
    ThreadLocal<Integer> threadLocalFied = ThreadLocal.withInitial(() -> 0);

    public void add() {
        threadLocalFied.set(1 + threadLocalFied.get());
    }
}

public class ThreadLocalDemo2 {
    public static void main(String[] args) {
        MyData myData = new MyData();

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                    try {
                        Integer beforeInt = myData.threadLocalFied.get();
                        myData.add();
                        Integer afterInt = myData.threadLocalFied.get();
                        System.out.println(Thread.currentThread().getName() + "\t" + "beforeInt：" + beforeInt + "\t" + "afterInt：" + afterInt);
                    } finally {
                        myData.threadLocalFied.remove();
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
