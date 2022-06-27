package juc.threadLocal;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class MyObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("------invoke finalize method~!!!");
    }
}

public class ReferenceDemo {
    public static void main(String[] args) {
        MyObject myObject = new MyObject();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomReference = new PhantomReference<>(myObject, referenceQueue);
        ArrayList<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(phantomReference.get() + "\t" + "list add ok");
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                Reference<?> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("------有虚对象回收加入了队列");
                    break;
                }
            }
        }, "t2").start();
    }

    private static void weakReference() {
        WeakReference<MyObject> myObjectWeakReference = new WeakReference<>(new MyObject());
        System.out.println("------gc after内存够用：" + myObjectWeakReference.get());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc after内存够用：" + myObjectWeakReference.get());
    }

    private static void softReference() {
        SoftReference<MyObject> softReference = new SoftReference<>(new MyObject());
        System.out.println("------softReference：" + softReference.get());

        System.gc();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("gc after内存够用：" + softReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("gc after内存不够用：" + softReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before：" + myObject);

        myObject = null;
        System.gc();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc after：" + myObject);
    }
}
