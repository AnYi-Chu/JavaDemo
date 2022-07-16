package com.atguigu.redis.bootredis02.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class GoodController {
    public static final String REDIS_LOCK = "atguiguLock";
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private Redisson redisson;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/buy_goods")
    public String buy_Goods() {
        RLock redissonLock = redisson.getLock(REDIS_LOCK);
        redissonLock.lock();
        try {
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
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()) {
                redissonLock.unlock();
            }
        }
    }
}
