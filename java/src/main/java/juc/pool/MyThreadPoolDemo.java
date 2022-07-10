package juc.pool;

import java.util.concurrent.*;

/**
 * 线程池
 * 作用：它控制运行线程的数量，在处理中将任务放入队列中，然后在线程创建后启动，如果线程数超过最大线程数量，超出的线程排队等待
 * 特点：线程复用，控制最大并发数，管理线程
 * 底层：ThreadPoolExecutor
 * 种类：分五种，实际三种
 * 参数：七个
 * corePoolSize核心线程数
 * maximumPoolSize最大线程数
 * keepAliveTime多余线程的存活时间
 * unit多余线程的存活时间的单位
 * workQueue任务队列
 * threadFactory生成工作线程的线程工厂数（一般用默认）
 * handler拒绝策略
 * <p>
 * 拒绝策略分
 * AbortPolicy(默认)直接抛出RejectedExecutionException异常
 * CallerRunsPolicy调用者机制，不会抛出异常，也不会放弃任务，而是将任务回退到调用者，从而降低新任务的流量
 * DiscardOldestPolicy放弃等待时间最久的未处理的任务，将被拒绝的任务添加到等待队列中
 * DiscardPolicy直接丢弃被拒绝的任务
 * <p>
 * 运行原理
 * 当调用execute()方法添加一个请求任务时，如果这时正在运行的线程数量小于corePoolSize，就马上创建线程并运行这个任务
 * 否则线程池将它放入队列，如果这时队列已满且正在运行的线程数量小于maximumPoolSize，就创建非核心线程并运行这个任务
 * 否则线程池启动饱和拒绝策略来执行
 * 当一个线程任务完成后，它会从队列中取下一个任务来执行
 * 如果当前运行线程数量超过corePoolSize，线程空闲时间达到keepAliveTime值，超出corePoolSize数的线程会被销毁
 * <p>
 * 线程容量=maximumPoolSize+workQueue
 * <p>
 * 业务分类
 * CPU密集型，尽可能少配置线程数量，CPU核数+1个线程的线程池
 * IO密集型，尽可能多配置线程数量，CPU核数*2或CPU核数/1-阻塞系数（阻塞系数一般在0.8~0.9）
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
        try {
            for (int i = 1; i <= 9; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }

    private static void threadPoolInit() {
        //一池固定线程数，它的corePoolSize和maximumPoolSize值等，使用LinkedBlockingQueue
        //ExecutorService threadPool = Executors.newFixedThreadPool(5);

        //一池一线程，它的corePoolSize和maximumPoolSize值是1，使用LinkedBlockingQueue
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();

        //一池多线程，它的corePoolSize值为0，maximumPoolSize值为Integer.MAX_VALUE，使用SynchronousQueue
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
