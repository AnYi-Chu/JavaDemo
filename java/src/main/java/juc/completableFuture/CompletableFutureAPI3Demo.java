package juc.completableFuture;

import java.util.concurrent.CompletableFuture;

/*
 * thenRun()任务A执行完执行任务B，任务B不需要A的结果
 * thenAccept()任务A执行完执行任务B，任务B需要A的结果，任务B无返回值
 * thenApply()任务A执行完执行任务B，任务B需要A的结果，任务B有返回值
 * */
public class CompletableFutureAPI3Demo {    //对计算结果进行消费
    public static void main(String[] args) {    //任务之间顺序执行
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {
        }).join()); //任务A执行完执行任务B，任务B不需要A的结果
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());   //任务A执行完执行任务B，任务B需要A的结果，任务B无返回值
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());    //任务A执行完执行任务B，任务B需要A的结果，任务B有返回值
    }
}
