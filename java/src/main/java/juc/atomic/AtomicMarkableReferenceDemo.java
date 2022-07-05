package juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/*
 * AtomicMarkableReference拥有是否标记，处理是否修改的问题
 * */
public class AtomicMarkableReferenceDemo {  //拥有标记的原子引用
    static AtomicMarkableReference markableReference = new AtomicMarkableReference(100, false);

    public static void main(String[] args) {
        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标识：" + marked);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            markableReference.compareAndSet(100, 1000, marked, !marked);
        }, "t1").start();

        new Thread(() -> {
            boolean marked = markableReference.isMarked();
            System.out.println(Thread.currentThread().getName() + "\t" + "默认标识：" + marked);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = markableReference.compareAndSet(100, 2000, marked, !marked);
            System.out.println(Thread.currentThread().getName() + "\t" + "t2：" + b);
            System.out.println(Thread.currentThread().getName() + "\t" + markableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "\t" + markableReference.getReference());
        }, "t2").start();
    }
}
