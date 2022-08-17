package com.atguigu.consumer;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer_PubSub1 {
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
        String queue0Name = "test_fanout_queue0";
        String queue1Name = "test_fanout_queue1";
        //接收消息
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Body" + new String(body));
            }
        };
        channel.basicConsume(queue1Name, true, consumer);
    }
}
