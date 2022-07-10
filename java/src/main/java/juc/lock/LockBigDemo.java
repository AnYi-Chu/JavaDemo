package juc.lock;

public class LockBigDemo {  //锁粗化
    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock) { //若方法中首尾相连，前后相邻的是同一个锁对象，JIT会将其合并成一个，避免此次申请和释放
                System.out.println("1");
            }
            synchronized (objectLock) {
                System.out.println("2");
            }
            synchronized (objectLock) {
                System.out.println("3");
            }
            synchronized (objectLock) {
                System.out.println("1");
                System.out.println("2");
                System.out.println("3");
                System.out.println("4");
            }
        }, "t1").start();
    }
}
