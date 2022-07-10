package juc.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized和lock区别
 * 1.原始构成
 * synchronized是关键字属于JVM层面，底层是通过monitor对象完成，其wait/notify方法也依赖于monitor对象，只有在同步块或方法中才能调用wait/notify等方法
 * lock是具体类属于API层面的锁
 * 2.使用方法
 * synchronized不需要用户手动释放锁，它会在执行完代码后自动释放33
 * ReentrantLock需要用户手动释放锁若没有主动释放锁，就可能导致死锁，需要lock()和unlock()配合try/finally完成
 * 3.等待是否可中断
 * synchronized不可中断，除非正常完成或抛出异常
 * ReentrantLock可中断，设置超时方法tryLock(long timeout,TimeUnit unit)，lockInterruptibly()放代码块中，调用interrupt()方法可中断
 * 4.加锁是否公平
 * synchronized非公平锁
 * ReentrantLock默认非公平锁，构造方法可以传入boolean值，true为公平锁，false为非公平锁
 * 5.锁绑定多个条件Condition
 * synchronized没有
 * ReentrantLock用来实现分组唤醒需要唤醒的线程们，可以精确唤醒，而不是像synchronized要么随机唤醒一个线程要么唤醒全部线程
 * <p>
 * 多线程之间按顺序调用，实现A->B->C三个线程启动，A5次->B10次->C15次，然后A5次->B10次->C15次，10轮
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}

class ShareResource {
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //判断
    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            //干活
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            //干活
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            //干活
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
