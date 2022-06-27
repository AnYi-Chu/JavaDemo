package juc.async;

import java.util.concurrent.TimeUnit;

/*
 * 如果用户线程全部结束意味着程序需要完成的业务操作已经结束，守护线程随着JVM一同结束工作*/
public class DaemonDemo {
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
