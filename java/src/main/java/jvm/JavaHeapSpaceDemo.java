package jvm;

/**
 * java.lang.OutOfMemoryError：Java heap space
 * 堆溢出
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        byte[] bytes = new byte[80 * 1024 * 1024];
    }
}
