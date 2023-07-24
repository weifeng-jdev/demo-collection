package com.amano._04_routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: RoutingInfoConsumer
 * @package com.amano._04_routing
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/23
 **/
public class RoutingInfoConsumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("info_queue", true, false, false, null);
        channel.queueDeclare("error_queue", true, false, false, null);
        channel.exchangeDeclare("routing", "direct");
        channel.queueBind("info_queue", "routing", "info");
        channel.basicConsume("info_queue", true, (consumerTag, message) -> {
            System.out.println("info消费者：" + new String(message.getBody()));
        }, consumerTag -> {
            System.out.println("消费者取消消费回调逻辑");
        });
    }
}
