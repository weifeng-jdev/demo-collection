package com.amano._05_topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: TopicInfoConsumer
 * @package com.amano._05_topic
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/23
 **/
public class TopicBizConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("topic", "topic");
        channel.queueDeclare("topic_queue1", true, false, false, null);
        channel.queueBind("topic_queue1", "topic", "biz.*");
        channel.basicConsume("topic_queue1", true, (consumerTag, message) -> {
            System.out.println("Biz消费者：" + new String(message.getBody()));
        }, consumerTag -> {
            System.out.println("消费者取消消费回调逻辑");
        });
    }
}
