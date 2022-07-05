import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁（递归锁）
 * 同一线程外层函数获得锁后，内层递归函数仍然能获取到该锁的代码
 * 在同一线程外部方法获得锁的时候，进入内部方法会自动获取锁
 * 即线程可以进入任何一个它已经拥有的锁所同步的着的代码块
 * <p>
 * synchronized是一个典型可重入锁
 * <p>
 * 可重入锁可以避免死锁
 */
public class ReenterLockDemo {
    public static void main(String[] args) throws Exception {
        Phone phone = new Phone();

        System.out.println("synchronized版可重入锁");
        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("lock版可重入锁");
        Thread t3 = new Thread(phone, "t3");
        Thread t4 = new Thread(phone, "t4");
        t3.start();
        t4.start();
    }
}

class Phone implements Runnable {
    public synchronized void sendSMS() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        //lock可以多加，但要注意加锁几次就要解锁几次
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        } finally {
            lock.unlock();
        }
    }
}
