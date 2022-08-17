package com.atguigu;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-rabbitmq-producer.xml")
public class ProducerTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testHelloWorld() {
        rabbitTemplate.convertAndSend("spring_queue", "皖南上天，芜湖起飞！");
    }

    @Test
    public void testFanout() {  //发送fanout消息
        rabbitTemplate.convertAndSend("spring_fanout_exchange", "", "皖南上天，芜湖起飞！");
    }

    @Test
    public void testTopic() {  //发送topic消息
        rabbitTemplate.convertAndSend("spring_topic_exchange", "heima.hehe", "皖南上天，芜湖起飞！");
    }

    @Test
    public void testConfirm() { //确认模式
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            System.out.println("confirm方法被执行！");
            if (ack) {
                System.out.println("消息接收成功" + cause);
            } else {
                System.out.println("消息接收失败" + cause);
            }
        });

        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "皖南上天，芜湖起飞！");
    }

    @Test
    public void testReturn() {  //回退模式
        rabbitTemplate.setMandatory(true);

        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            System.out.println("return方法被执行！");
            System.out.println(message);
            System.out.println(replyCode);
            System.out.println(replyText);
            System.out.println(exchange);
            System.out.println(routingKey);
        });

        rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "皖南上天，芜湖起飞！");
    }

    @Test
    public void testSend() {    //限流
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("test_exchange_confirm", "confirm", "皖南上天，芜湖起飞！");
        }
    }

    @Test
    public void testTTL() { //过期时间
        //测试消息统一过期
//        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.convertAndSend("spring_topic_exchange", "ttl.hehe", "皖南上天，芜湖起飞！");
//        }

        //消息后处理对象，设置消息的参数信息
        MessagePostProcessor messagePostProcessor = message -> {
            message.getMessageProperties().setExpiration("5000");   //设置消息过期时间
            return message;
        };
        //设置消息单独过期
        rabbitTemplate.convertAndSend("test_exchange_ttl", "ttl.hehe", "皖南上天，芜湖起飞！", messagePostProcessor);
    }

    @Test
    public void testDlx() {    //死信队列
        //测试过期时间，死信消息
//        rabbitTemplate.convertAndSend("test_exchange_dlx", "test.dlx.haha", "皖南上天，芜湖起飞！");

        //测试长度限制后，死信消息
//        for (int i = 0; i < 20; i++) {
//            rabbitTemplate.convertAndSend("test_exchange_dlx", "test.dlx.haha", "皖南上天，芜湖起飞！");
//        }

        //测试消息拒收，死信消息
        rabbitTemplate.convertAndSend("test_exchange_dlx", "test.dlx.haha", "皖南上天，芜湖起飞！");
    }

    @Test
    public void testDelay() throws InterruptedException {    //延迟队列
        rabbitTemplate.convertAndSend("order_exchange", "order.msg", "皖南上天，芜湖起飞！");

        for (int i = 0; i < 10; i++) {
            System.out.println(i + "...");
            Thread.sleep(1000);
        }
    }
}
