package com.amano._03_publish;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: PublishSubConsumer
 * @package com.amano._03_publish
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/19
 **/
public class PublishSubConsumer01 {
    public static void main(String[] args) throws IOException, TimeoutException {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2. 设置连接参数
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("rabbit");
        // 3. 创建连接 创建channel
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        // 4. 声明队列 绑定交换机
        channel.exchangeDeclare("publish-sub-exchange", "direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "publish-sub-exchange", "");
        // 5. 设置每次只能消费一个消息
        channel.basicQos(1);
        // 6. 读取消息
        channel.basicConsume(queue, false, (s, delivery) -> {
            System.out.println("consumer01: 收到消息：" + new String(delivery.getBody()));
            try {
                Thread.sleep(3000);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, s -> System.out.println("接收消息失败"));
    }
}
