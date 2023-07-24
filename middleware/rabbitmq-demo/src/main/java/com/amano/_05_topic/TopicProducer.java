package com.amano._05_topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: TopicProducer
 * @package com.amano._05_topic
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/23
 **/
public class TopicProducer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");

        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare("topic", "topic");
            channel.queueDeclare("topic_queue1", true, false, false, null);
            channel.queueDeclare("topic_queue2", true, false, false, null);
            channel.queueBind("topic_queue1", "topic", "biz.*");
            channel.queueBind("topic_queue2", "topic", "log.*");
            channel.basicPublish("topic", "log.error", null, "log.error".getBytes());
            channel.basicPublish("topic", "log.info", null, "log.info".getBytes());
            channel.basicPublish("topic", "log.debug", null, "log.debug".getBytes());
            channel.basicPublish("topic", "biz.success", null, "biz.success".getBytes());
            System.out.println("消息发送成功");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
