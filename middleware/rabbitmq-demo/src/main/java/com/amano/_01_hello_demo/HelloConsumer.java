package com.amano._01_hello_demo;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: HelloConsumer
 * @package com.amano._01_hello_demo
 * @description: 最基本的点对点直接发送消息
 * @author: weifeng
 * @date: 2023/7/19
 **/
public class HelloConsumer {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2. 设置连接参数
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");
        // 3. 创建连接 创建channel
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
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
        // 5. 读取消息
        channel.basicConsume(QUEUE_NAME, false, new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                System.out.println("收到消息：" + new String(delivery.getBody()));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }, new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
                System.out.println("接收消息失败");
            }
        });
    }
}
