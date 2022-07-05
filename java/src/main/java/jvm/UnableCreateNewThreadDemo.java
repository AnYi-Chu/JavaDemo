package jvm;

/**
 * java.lang.OutOfMemoryError:unable to create new native thread
 * 原因
 * 一个进程中创建的线程数超上限
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("i：" + i);
            new Thread(() -> {
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "" + i).start();
        }
    }
}
