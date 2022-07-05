package jvm;

import java.nio.ByteBuffer;

/**
 * java.lang.OutOfMemoryError:Direct buffer memory
 * 原因
 * 写NIO程序经常使用ByteBuffer来读取或者写入数据，这是一种基于通道与缓冲区的I/O方式，它可以使用Native函数库直接分配堆外内存，然后通过一个储存在java堆里面的DirectByteBuffer对象作为这块内存的引用进行操作，这样避免了在java堆和navtive堆中来回复制数据，提高了性能
 * ByteBuffer.allocate(capability)分配JVM堆内存，属于GC管辖范围，需要拷贝所以速度较慢
 * ByteBuffer.allocateDirect(capability)非陪OS本地内存，不属于GC管辖范围，不需要拷贝所以速度较快
 * 如果不断分配本地内存而不是用堆内存，在本地内存用光后程序会直接崩溃出现OutOfMemoryError
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024) + "MB");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
