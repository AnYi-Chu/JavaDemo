package juc.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureAPIDemo { //获得计算和触发计算
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

//        System.out.println(completableFuture.get());    //不见不散
//        System.out.println(completableFuture.get(2L, TimeUnit.SECONDS)); //过时不候
//        System.out.println(completableFuture.join());   //类似get
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println(completableFuture.getNow("xxx"));    //立即获得结果不阻塞
        System.out.println(completableFuture.complete("completeValue") + "\t" + completableFuture.join());  //是否打断get或join方法立即返回括号值
    }
}
