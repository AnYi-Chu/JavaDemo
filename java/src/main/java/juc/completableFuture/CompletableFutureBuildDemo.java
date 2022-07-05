package juc.completableFuture;

import java.util.concurrent.*;

/*
 * runAsync()没有返回值，默认使用ForkJoinPoll.commonPoll()线程池
 * supplyAsync()有返回值，默认使用ForkJoinPoll.commonPoll()线程池
 * */
public class CompletableFutureBuildDemo {   //异步
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> { //没有返回值，默认使用ForkJoinPoll.commonPoll()线程池
//            System.out.println(Thread.currentThread().getName());
//            try {
//                TimeUnit.SECONDS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, threadPool);

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello supplyAsync";
        }, threadPool);

        System.out.println(future.get());

        threadPool.shutdown();
    }
}
