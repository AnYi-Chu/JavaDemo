package com.atguigu.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_WorkQueues0 {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置参数
        factory.setHost("127.0.0.1");   //地址
        factory.setPort(5672);  //端口
        factory.setVirtualHost("/alienware");   //虚拟机
        factory.setUsername("gaohao");  //用户名
        factory.setPassword("gaohao");  //密码
        //创建链接
        Connection connection = factory.newConnection();
        //创建channel
        Channel channel = connection.createChannel();
        //创建队列
        channel.queueDeclare("work_queues", true, false, false, null);
        //接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("ConsumerTag" + consumerTag);
                System.out.println("Exchange" + envelope.getExchange());
                System.out.println("RoutingKey" + envelope.getRoutingKey());
                System.out.println("Properties" + properties);
                System.out.println("Body" + new String(body));
            }
        };
        channel.basicConsume("work_queues", true, consumer);
    }
}
