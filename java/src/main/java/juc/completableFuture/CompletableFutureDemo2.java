package juc.completableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> thenCombineResult = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            return 20;
        }), (x, y) -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            return 30;
        }), (a, b) -> {
            System.out.println(Thread.currentThread().getName() + "\t ------come in");
            return a + b;
        });
        System.out.println("主线程结束");
        System.out.println(thenCombineResult.get());
    }
}
