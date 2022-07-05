package juc.lockSupport;

/*
 * 中断状态根据传入的ClearInterrupted参数值来确定是否重置
 * 静态方法interrupted()传入参数为true，实例方法isInterrupted()传入参数为false*/
public class InterruptDemo4 {   //线程中断
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println("------1");
        Thread.currentThread().interrupt();
        System.out.println("------2");
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());
        System.out.println(Thread.currentThread().getName() + "\t" + Thread.interrupted());

        Thread.interrupted();   //静态方法
        Thread.currentThread().isInterrupted(); //实例方法
    }
}
