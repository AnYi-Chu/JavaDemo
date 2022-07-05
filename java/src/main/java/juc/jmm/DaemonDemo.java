package juc.jmm;

import java.util.concurrent.TimeUnit;

/*
 * 用户线程是默认的系统的工作线程
 * 守护线程是为其他线程服务的线程，当用户线程全部结束后，它会随着Java虚拟机会自动退出*/
public class DaemonDemo {   //用户线程与守护线程
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t 开始运行，" + ((Thread.currentThread().isDaemon()) ? "守护线程" : "用户线程"));
            while (true) {

            }
        }, "t1");
        t1.setDaemon(true); //必须在start()前设置，否则报错java.lang.IllegalThreadStateException
        t1.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "\t ------end 主线程");
    }
}
