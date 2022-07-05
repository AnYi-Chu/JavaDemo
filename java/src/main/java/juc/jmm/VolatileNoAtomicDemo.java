package juc.jmm;

import java.util.concurrent.TimeUnit;

/*
 * volatile保证线程在数据加载时是最新值，如果第二个线程在第一个线程读取旧值到写回新值之间读取值，会造成脏读
 * */
class MyNumber {
    volatile int number;

    public void addPlusPlus() {
        number++;
    }
}

public class VolatileNoAtomicDemo { //volatile原子性
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        for (int i = 0; i <= 10; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myNumber.number);
    }
}
