package juc.lock;

public class LockClearUPDemo {  //锁消除
    static Object objectLock = new Object();

    public void m1() {
        Object o = new Object();
        synchronized (o) {  //该锁对象没有被公用扩散到其他线程使用
            System.out.println(o.hashCode() + "\t" + objectLock.hashCode());
        }
    }

    public static void main(String[] args) {
        LockClearUPDemo lockClearUPDemo = new LockClearUPDemo();
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                lockClearUPDemo.m1();
            }, String.valueOf(i)).start();
        }
    }
}
