package com.amano._01_hello_demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: HelloProduce
 * @package com.amano._01_hello_demo
 * @description: 最基本的点对点直接发送消息
 * @author: weifeng
 * @date: 2023/7/19
 **/
public class HelloProduce {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2. 设置连接参数
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");
        // 3. 创建连接 创建channel
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel();) {
            /*
             * 4. 声明队列
             * 参数:
             * 1. 队列名称
             * 2. 是否持久化
             * 3. 是否排外，是否独占独立
             * 4. 是否自动删除，随着最后一个消费者消息完毕消息以后是否把队列删除
             * 5. 携带附属参数
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // 5. 发送消息
            channel.basicPublish("", QUEUE_NAME, null, "hello rabbitmq".getBytes());
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
