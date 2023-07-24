package com.amano._04_routing;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: RoutingProducer
 * @package com.amano._04_routing
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/21
 **/
public class RoutingProducer {
    public static void main(String[] args) {
        // 1.
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");

        try (
                Connection connection = connectionFactory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare("routing", "direct");
            channel.queueDeclare("info_queue", true, false, false, null);
            channel.queueDeclare("error_queue", true, false, false, null);
            channel.queueBind("info_queue", "routing", "info");
            channel.queueBind("error_queue", "routing", "error");
            for (int i = 0; i < 10; i++) {
               if (i % 2 == 0) {
                   channel.basicPublish("routing", "info", null, ("info" + i).getBytes());
               } else {
                   channel.basicPublish("routing", "error", null, ("error" + i).getBytes());
               }
                System.out.println("消息发送成功");
            }
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
