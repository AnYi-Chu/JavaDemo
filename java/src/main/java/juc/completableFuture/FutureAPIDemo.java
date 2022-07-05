package juc.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FutureAPIDemo {    //get()阻塞和isDone()轮询
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "task over";
        });
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();


        System.out.println(Thread.currentThread().getName() + "\t ------忙其他任务了");

//        System.out.println(futureTask.get());   //get容易导致阻塞，建议放在程序后
//        System.out.println(futureTask.get(3, TimeUnit.SECONDS));

        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在处理中");
            }
        }
    }
}
