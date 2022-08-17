package com.atguigu.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer_Routing {
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
        //创建交换机
        String exchangeName = "test_direct";
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, false, null);
        //创建队列
        String queue0Name = "test_direct_queue0";
        String queue1Name = "test_direct_queue1";
        channel.queueDeclare(queue0Name, true, false, false, null);
        channel.queueDeclare(queue1Name, true, false, false, null);
        //绑定队列和交换机
        channel.queueBind(queue0Name, exchangeName, "error");
        channel.queueBind(queue1Name, exchangeName, "info");
        channel.queueBind(queue1Name, exchangeName, "error");
        channel.queueBind(queue1Name, exchangeName, "warning");
        //发送消息
        String body = "日志信息：皖南上天，芜湖起飞！";
        channel.basicPublish(exchangeName, "warning", null, body.getBytes());
        //释放资源
        channel.close();
        connection.close();
    }
}
