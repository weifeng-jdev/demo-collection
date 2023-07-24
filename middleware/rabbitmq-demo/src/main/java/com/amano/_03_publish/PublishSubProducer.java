package com.amano._03_publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: PublishSubProducer
 * @package com.amano._03_publish
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/19
 **/
public class PublishSubProducer {
    public static void main(String[] args) {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2. 设置连接参数
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("rabbit");
        connectionFactory.setPassword("rabbit");
        // 3. 创建连接 创建channel
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel();) {
            /*
             * 4. 声明交换机
             * 参数:
             * 1. 交换机名称
             * 2. 交换机类型:
             *  i: direct 默认交换机 通过routingKey完全匹配
             *  ii: topic 通配符匹配
             *  iii: fanout 广播交换机
             *  iv: headers 通过headers匹配
             */
            channel.exchangeDeclare("publish-sub-exchange", "direct");
            // 5. 发送消息
            for (int i = 0; i < 10; i++) {
                channel.basicPublish("publish-sub-exchange", "", null, ("hello rabbitmq" + i).getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
