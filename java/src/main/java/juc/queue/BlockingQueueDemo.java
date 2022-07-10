package juc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 阻塞队列
 * 即在并发环境下，线程会在特定情况下挂起，符合条件后又被自动唤醒
 * 优点：实现线程托管
 * <p>
 * 实现：
 * ArrayBlockingQueue数组组成的有界阻塞队列
 * LinkedBlockingQueue链表组成的有界阻塞队列（大小默认为Integer.MAX_VALUE）
 * PriorityBlockingQueue支持优先级排序的无界阻塞队列
 * DelayQueue使用优先级队列实现延迟的无界阻塞队列
 * SynchronousQueue不存元素的阻塞队列
 * LinkedTransferQueue链表组成的无界阻塞队列
 * LinkedBlockingDeque链表组成双向阻塞队列
 * <p>
 * MQ消息中间件的底层原理
 * 线程池底层为：ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> blockingQueue1 = new LinkedBlockingQueue<>(3);

        System.out.println("抛出异常API");
        //当阻塞队列为满时，向队列中添加元素的操作会被阻塞java.lang.IllegalStateException: Queue full
        System.out.println(blockingQueue.add("a"));
        //当阻塞队列为空时，从队列中获取元素的操作会被阻塞java.util.NoSuchElementException
        System.out.println(blockingQueue.remove());
        //检查是否为null，并返回第一个元素
        System.out.println(blockingQueue.element());

        System.out.println("特殊值API");
        //插入方法，成功ture，否则false
        System.out.println(blockingQueue.offer("b"));
        //移除方法，成功返回队列元素，如果队列里没有元素就返回null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.peek());

        System.out.println("阻塞API");
        //当阻塞队列已满，生产者线程继续往队列里put元素，队列会一直阻塞生产者线程直到put数据或响应中断退出
        //System.out.println(blockingQueue.put("b"));
        //当阻塞队列为空，消费者线程从队列中take元素，队列会一直阻塞消费者线程直到队列可用
        //System.out.println(blockingQueue.take());

        System.out.println("API");
        //System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
    }
}
