package juc.lock;

import java.util.concurrent.TimeUnit;

class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("------sendEmail");
    }

    public synchronized void sendSMS() {
        System.out.println("------sendSMS");
    }

    public void hello() {
        System.out.println("------hello");
    }
}

/*
 * 1.一个对象里如果有多个synchronized方法，在某一时刻内只能由唯一一个线程访问这些synchronized方法，即使用对象锁（堆）后，其他线程不能进入当前实例对象的其他的synchronized方法
 * 2.一个对象里如果有多个static synchronized方法，在某一时刻内只能由唯一一个线程访问这些static synchronized方法，即使用类锁（方法区）后，其他线程不能进入该类的实例对象的static synchronized方法
 * 3.static synchronized方法和synchronized方法之间不会有竞态条件*/
public class Lock8Demo {    //锁应用
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
//            phone.sendSMS();
//            phone.hello();
//            phone2.sendSMS();
        }, "b").start();
    }
}
