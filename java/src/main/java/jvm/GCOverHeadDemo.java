package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * GC回收时间过长会爆出java.lang.OutOfMemoryError：GC overhead limit exceeded
 * 过长定义是超过98%的时间来做GC并且回收了不到2%的堆内存
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<String> list = new ArrayList<>();

        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Throwable e) {
            System.out.println("i:" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
