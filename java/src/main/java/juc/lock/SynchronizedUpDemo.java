package juc.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/*
 * 锁升级：无锁->偏向锁->轻锁->重锁
 * */
public class SynchronizedUpDemo {
    public static void main(String[] args) {    //锁升级
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object o = new Object();
//        System.out.println("十进制：" + o.hashCode());
//        System.out.println("十六进制：" + Integer.toHexString(o.hashCode()));
//        System.out.println("二进制：" + Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("------------------------------------------------");

        new Thread(() -> {
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1").start();
    }
}
