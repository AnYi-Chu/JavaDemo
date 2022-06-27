package juc.async;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
/*
* Future接口可以为主线程开一个分支任务，专门为主线程处理耗时费力的复杂业务
* 多线程/返回值/异步任务
* */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<>(new MyThread());
        Thread t1 = new Thread(futureTask, "t1");
        t1.start();
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("------come in call()");
        return "hello Callable";
    }
}