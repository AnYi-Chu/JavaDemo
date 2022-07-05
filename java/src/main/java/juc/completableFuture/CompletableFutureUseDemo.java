package juc.completableFuture;

import java.util.concurrent.*;

/*
 * 函数式接口名称   方法名称      参数          返回值
 * Runnable      run         无参数         无返回值
 * Function      apply       1个参数        有返回值
 * Consume       accept      1个参数        无返回值
 * Supplier      get         无参数         有返回值
 * BiConsumer    accept      2个参数        无返回值
 * */
public class CompletableFutureUseDemo { //多异步方法执行
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        try {
            CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName() + "------come in");
                int result = ThreadLocalRandom.current().nextInt(10);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("------1s后出结果：" + result);
                if (result > 5) {
                    int i = 10 / 0; //触发异常
                }
                return result;  //result就是v
            }, threadPool).whenComplete((v, e) -> { //第一步完成后执行
                if (e == null) {
                    System.out.println("------计算完成，更新系统updateValue：" + v);
                }
            }).exceptionally(e -> { //异常处理
                e.printStackTrace();
                System.out.println("异常情况：" + e.getCause() + "\t" + e.getMessage());
                return null;
            });
            System.out.println(Thread.currentThread().getName() + "\t 线程先去忙其他任务");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

//        try {
//            TimeUnit.SECONDS.sleep(3);  //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立即关闭
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static void future1() throws InterruptedException, ExecutionException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "------come in");
            int result = ThreadLocalRandom.current().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("------1s后出结果：" + result);
            return result;
        });

        System.out.println(Thread.currentThread().getName() + "\t 线程先去忙其他任务");

        System.out.println(completableFuture.get());
    }
}
