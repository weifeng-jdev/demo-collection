package com.amano.rabbitmqdemo.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @className: AckConsumer
 * @package com.amano.rabbitmqdemo.listener
 * @description: 手动ack消息监听者
 * @author: amano
 * @date: 2023/6/26
 **/
@Component
public class AckConsumer implements ChannelAwareMessageListener {

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        System.out.println("receive msg:" + new String(message.getBody()));
        // 手动确认消息
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
