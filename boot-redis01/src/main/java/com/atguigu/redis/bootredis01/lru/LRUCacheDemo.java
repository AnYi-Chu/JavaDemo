package com.atguigu.redis.bootredis01.lru;

import java.util.LinkedHashMap;
import java.util.Map;
/*
* 最右边是最频繁最近使用，同时会淘汰最左边的元素
*
* */
public class LRUCacheDemo<K, V> extends LinkedHashMap<K, V> {   //使用LinkedHashMap完成LRU算法
    private int capacity;

    public LRUCacheDemo(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1, "a");
        lruCacheDemo.put(2, "b");
        lruCacheDemo.put(3, "c");
        System.out.println(lruCacheDemo.keySet());
    }
}
