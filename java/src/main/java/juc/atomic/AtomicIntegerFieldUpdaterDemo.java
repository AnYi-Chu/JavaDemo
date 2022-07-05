package juc.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

class BankAccount {
    String bankName = "CCB";
    public volatile int money = 0;  //修改字段必须是public和volatile修饰

    public synchronized void add() {    //加重锁
        money++;
    }

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(BankAccount.class, "money");

    public void transMoney(BankAccount bankAccount) {
        fieldUpdater.getAndIncrement(bankAccount);
    }
}

/*
 * AtomicIntegerFieldUpdater.newUpdater(类对象,字段名)，要求字段名必须是public和volatile修饰
 * */
public class AtomicIntegerFieldUpdaterDemo {    //以一种线程安全的方式操作非线程安全对象内的某些字段
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= 1000; j++) {
//                        bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + "\t" + "result：" + bankAccount.money);
    }
}
