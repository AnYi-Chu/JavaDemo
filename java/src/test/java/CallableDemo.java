import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Callable接口创建线程
 * <p>
 * 创建线程方式
 * 继承Thread
 * 实现Runnable(没有返回值，不抛出异常)
 * 实现Callable（有返回值，会抛出异常）
 * 利用线程池
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //FutureTask(Callable<V> callable)
        //两个线程，main和AA线程
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());
        new Thread(futureTask, "AA").start();
        new Thread(futureTask2, "BB").start();

        System.out.println(Thread.currentThread().getName() + "*****");
        int result01 = 1000;

        while (!futureTask.isDone()) {
        }

        //获得Callable线程的计算结果，如果没有计算完成就强求，这回导致阻塞，值要计算完成
        int result02 = futureTask.get();
        System.out.println("result:" + (result01 + result02));
    }
}

class MyThread implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(Thread.currentThread().getName() + "come in callable");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}
