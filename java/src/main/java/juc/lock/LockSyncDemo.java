package juc.lock;

/*
 * javap -c ***.class
 * javap -v ***.class
 * */
public class LockSyncDemo { //反编译
    Object object = new Object();

    public void m1() {
        synchronized (object) {
            System.out.println("------hello synchronized code block");
            throw new RuntimeException("------exception");
        }
    }

    public synchronized void m2() {
        System.out.println("------hello synchronized m2");
    }

    public static synchronized void m3() {
        System.out.println("------hello static synchronized m3");
    }


    public static void main(String[] args) {

    }
}
