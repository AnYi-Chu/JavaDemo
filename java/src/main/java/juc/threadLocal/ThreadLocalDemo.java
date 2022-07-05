package juc.threadLocal;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class House {
    int saleCount = 0;

    public synchronized void saleHouse() {
        ++saleCount;
    }

    //    ThreadLocal<Integer> saleVolume = new ThreadLocal<Integer>() {
//        @Contract(pure = true)
//        @Override
//        protected @NotNull Integer initialValue() {
//            return 0;
//        }
//    };
    static ThreadLocal<Integer> saleVolume = ThreadLocal.withInitial(() -> 0); //必须初始化，防止空指针异常

    public void saleVolumeByThreadLocal() {
        saleVolume.set(1 + saleVolume.get());
    }
}

/*
 * ThreadLocal不解决线程间共享数据的问题，适用于变量在线程间隔且在方法间共享的场景，通过隐式创建各个线程专属的实例副本避免线程安全的问题
 *      每个线程持有一个专属的Map并维护了ThreadLocal对象与具体实例的映射，该Map只能被持有它的线程访问，不存在线程安全及锁的问题
 *      expungeStaleEntry()、cleanSomeSlots()、replaceStaleEntry()会回收key是null的Entry对象的值（具体实例）以及Entry本身从而防止内存泄漏
 * 并发条件下防止线程争抢资源的方法：
 *      ThreadLocal实现线程专属的实例副本
 *      加锁控制资源访问顺序
 * ThreadLocal使用必须初始化withInitial()和回收remove()，建议static修饰
 * Thread、ThreadLocal、ThreadLocalMap关系类似自然人、身份证、身份证上的名字
 * ThreadLocalMap是ThreadLocal的静态内部类，它是保存ThreadLocal弱引用对象的map（key为ThreadLocal），这样可以让每个线程都拥有专属的变量
 *      如果key引用是强引用，当key指向的ThreadLocal对象及v指向的对象不能被gc回收，会造成内存泄漏，弱引用可以避免，set()、get()、remove()都有key=null，实现删除脏Entry
 * */
public class ThreadLocalDemo {  //本地线程
    public static void main(String[] args) {
        House house = new House();

        for (int i = 1; i <= 5; i++) {
            new Thread(() -> {
                int size = new Random().nextInt(5) + 1;
                try {
                    for (int j = 1; j <= size; j++) {
                        house.saleHouse();
                        house.saleVolumeByThreadLocal();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t" + "号卖出：" + house.saleVolume.get());
                } finally {
                    house.saleVolume.remove();  //防止内存泄露
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t" + "共计卖出多少套：" + house.saleCount);
    }
}
