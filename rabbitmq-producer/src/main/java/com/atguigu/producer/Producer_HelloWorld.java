package com.atguigu.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer_HelloWorld {
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
        channel.queueDeclare("hello_world", true, false, false, null);
        //发送消息
        String body = "hello rabbitmq";
        channel.basicPublish("", "hello_world", null, body.getBytes());
        //释放资源
        channel.close();
        connection.close();
    }
}
