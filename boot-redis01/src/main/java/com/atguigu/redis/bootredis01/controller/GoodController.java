package com.atguigu.redis.bootredis01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class GoodController {
    public static final String REDIS_LOCK = "atguiguLock";
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/buy_goods")
    public String buy_Goods() { //事务实现分布式锁
        String value = UUID.randomUUID() + Thread.currentThread().getName();
        try {
            Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK, value, 10L, TimeUnit.SECONDS);
            if (flag) {
                return "抢锁失败";
            }

            String result = stringRedisTemplate.opsForValue().get("goods:001");
            int goodNumber = result == null ? 0 : Integer.parseInt(result);

            if (goodNumber > 0) {
                int realNumber = goodNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                System.out.println("成功买到商品，库存还剩下：" + realNumber + "\t 服务提供端口：" + serverPort);
                return "成功买到商品，库存还剩下：" + realNumber + "\t 服务提供端口：" + serverPort;
            } else {
                System.out.println("商品已售完，服务提供端口：" + serverPort);
            }
            return "商品已售完，服务提供端口：" + serverPort;
        } finally {
            while (true) {
                stringRedisTemplate.watch(REDIS_LOCK);  //监控
                if (stringRedisTemplate.opsForValue().get(REDIS_LOCK).equalsIgnoreCase(value)) {    //判断锁所有
                    stringRedisTemplate.setEnableTransactionSupport(true);  //开启事务
                    stringRedisTemplate.multi();
                    stringRedisTemplate.delete(REDIS_LOCK); //解锁
                    List<Object> list = stringRedisTemplate.exec();
                    if (list == null) {
                        continue;
                    }
                }
                stringRedisTemplate.unwatch();  //解锁
                break;
            }
        }
    }
}
