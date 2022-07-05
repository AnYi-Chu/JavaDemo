package juc.lock;

import java.util.concurrent.locks.ReentrantLock;

/*
 * new ReentrantLock(true)公平锁即先到先得
 * new ReentrantLock(false)非公平锁即抢夺
 * */
class Ticket {  //公平锁与非公平锁
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);   //默认非公平锁，尽量减少CPU切换的时间

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：\t" + (number--) + "\t 还剩下：" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 55; i++) {
                ticket.sale();
            }
        }, "c").start();
    }
}
