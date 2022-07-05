package juc.jmm;

import java.util.concurrent.TimeUnit;

/*
 * 三大特性：可见性，原子性，有序性
 * 先行发生原则：如果a操作happens-before于b操作，a操作的执行结果对b操作可见，且a操作的执行顺序在b操作之前；
 *      ab两操作之间存在happens-before关系，并不意味着一定要按照该原则指定的顺序执行，如果重排序之后的执行结果与按照该原则指定的顺序执行的结果一致，那这种重排序并不非法
 * happens-before：次序规则，锁定规则，volatile变量规则，传递规则，线程启动规则，线程中断规则，线程终止规则，线程终结规则
 * 内存屏障：读读，写写，读写，写读；
 *      第一个是volatile读不允许重排序，第二个是volatile写不允许重排序，第一个是volatile写第二个是volatile读不允许重排序
 * */
public class VolatileSeeDemo {  //volatile可见性
    //    static boolean flag = true;
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t ------flag被设置为false，程序停止");
        }, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
        System.out.println(Thread.currentThread().getName() + "\t 修改完成");
    }
}
