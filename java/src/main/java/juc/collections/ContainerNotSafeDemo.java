package juc.collections;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类不安全问题
 * HashSet底层是HashMap，用key来存值，value放Object常量
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        /*List<String> list = Arrays.asList("a", "b", "c");
        list.forEach(System.out::println);*/
        List<Integer> list = new ArrayList<>();
        list.stream().sorted();
        Optional.of(list);
        for (Integer i : list) {
        }
        list.forEach(i -> {
        });
        List<Integer> ls = new ArrayList<>();
        System.arraycopy(list, 0, ls, 0, 1);
        //并发修改异常：java.util.ConcurrentModificationException

        /**
         * 1.故障现象
         *  java.util.ConcurrentModificationException
         * 2.导致原因
         *  并发争抢修改导致
         * 3.解决方案
         *  3.1new Vector()
         *  3.2集合工具类
         *      Collections.synchronizedList(new ArrayList<>())
         *      Collections.synchronizedSet(new HashSet<>())
         *      Collections.synchronizedMap(new HashMap<>())
         *  3.3写时复制
         *      new CopyOnWriteArrayList<>()
         *      new CopyOnWriteArraySet<>()
         *      new ConcurrentHashMap<>()
         * 4.优化建议
         */

        /**
         * CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器Object[]添加，而是先将当前容器Object[]进行copy，
         * 复制出一个新的容器Object[] newElements，然后往新的容器里添加元素，接着将原容器的引用指向新的容器setArray（newElements）；
         * 由于读和写作用不同的容器，这样做的好处是可以对CopyOnWrite容器进行并发读，而不用加锁。
         * 所以CopyOnWrite容器是一种读写分离的思想。
         */
    }

    public static void listNotSafe() {
        List<String> list = new ArrayList<String>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, "").start();
        }
    }

    public static void setNotSafe() {
        Set<String> set = new HashSet<String>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, "").start();
        }
    }

    public static void mapNotSafe() {
        Map<String, String> map = new HashMap<String, String>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            }, "").start();
        }
    }
}
