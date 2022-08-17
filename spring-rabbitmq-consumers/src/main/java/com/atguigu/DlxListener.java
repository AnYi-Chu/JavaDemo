package com.atguigu;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

@Component
public class DlxListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            System.out.println(new String(message.getBody()));
            System.out.println("处理业务逻辑...");
            int i = 3 / 0;
            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            System.out.println("出现异常，拒绝接收！");
            channel.basicNack(deliveryTag, true, false);
        }
    }
}
