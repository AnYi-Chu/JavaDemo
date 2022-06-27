package juc.interrupt;

/*
 * 1.interrupt()设置目标线程中断标志位true
 * 2.isInterrupted()判断当前线程是否被中断并获取中断标志
 * 3.interrupted()获取中断标志并重置中断标志
 * 4.静态方法interrupted()传入参数为true，实例方法isInterrupted()传入参数为false*/
public class InterruptDemo4 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("------1");
        Thread.currentThread().interrupt();
        System.out.println("------2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());

        Thread.interrupted();
        Thread.currentThread().isInterrupted();
    }
}
