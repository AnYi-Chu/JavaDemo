package juc.lock;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/*
 * 锁升级：无锁（001）->偏向锁（101）->轻锁（00）->重锁（10）
 * 无锁：初始状态，一个对象被实例化后，如果还没有被任何线程竞争锁，那它就为无锁状态
 * 偏向锁：单线程竞争，检查锁的MarWord类里面放的线程id是否相同，相同不需要加锁和释放锁，不相同尝试用CAS更新MarWord类里面放的线程id，成功锁不会升级，失败可能升级为轻量级锁，它在竞争时释放锁
 * 轻量级锁：多线程竞争，任意时刻最多只有一个线程竞争，竞争不激烈
 *      加锁：为线程创建专属的存储锁记录的空间（Displaced Mark Word），在获取锁时将MarkWord复制到Displaced Mark Word，尝试用CAS将锁的MarkWord替换为指向锁记录的指针，成功当前线程获得锁，失败自旋CAS
 *      释放：当前线程退出方法时释放，用CAS将Displaced Mark Word内容复制回锁的MarkWord里，成功没有竞争，失败释放锁并唤醒被阻塞的线程
 * 重量级锁：基于进入和退出Monitor对象实现，在开始位置插入monitor enter指令，在结束位置插入monitor exit
 ****************************************************************************************************
 * hashcode变化
 * 无锁状态下调用hashcode方法会生成hashcode码，并无法进入偏向锁状态，
 * 偏向锁状态下线程id和epoch值覆盖hashcode码，需要升级到重量级锁才能获取hashcode码
 * 轻量级锁状态下hashcode码拷贝在锁记录（Lock Record）空间里，锁释放后会回写到对象头
 * 重量级锁状态下hashcode码保存在ObjectMonitor类里，锁释放后会回写到对象头
 * */
public class SynchronizedUpDemo {   //锁升级

    private static Object objectLock = new Object();

    public static void main(String[] args) {
        try {
            TimeUnit.SECONDS.sleep(5);  //偏向锁延迟4秒启动
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object o = new Object();
//        System.out.println("本应该是偏向锁");
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//
//        o.hashCode();
//
//        synchronized (o) {
//            System.out.println("由于计算过一致性哈希，升级成轻量级锁");
//            System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        }

        synchronized (o) {
            o.hashCode();
            System.out.println("由于计算过一致性哈希，升级成重量锁级锁");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }

    private static void weightLock() {    //重量锁
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println(ClassLayout.parseInstance(objectLock).toPrintable());
            }
        }, "t2").start();
    }

    private static void biasLock() {   //偏向锁
//        try {
//            TimeUnit.SECONDS.sleep(5);  //偏向锁延迟4秒启动
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        Object o = new Object();

        System.out.println(ClassLayout.parseInstance(o).toPrintable()); //由于对象未用synchronized加锁，所以线程id是空

        System.out.println("========================================");

        new Thread(() -> {
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable()); //偏向锁带线程id
            }
        }, "t1").start();
    }

    private static void noLock() {    //无锁
        Object o = new Object();
        System.out.println("十进制：" + o.hashCode());
        System.out.println("十六进制：" + Integer.toHexString(o.hashCode()));
        System.out.println("二进制：" + Integer.toBinaryString(o.hashCode()));
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
