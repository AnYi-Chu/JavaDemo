package juc.async;

import java.util.concurrent.*;

public class CompletableFutureBuildDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

//        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> { //默认使用ForkJoinPoll.commonPoll()线程池
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
