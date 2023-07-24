package com.amano._02_work_queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: WorkQueueProduce
 * @package com.amano._02_work_queue
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/19
 **/
public class WorkQueueProduce {
    public static void main(String[] args) {
        // 1. 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 2. 设置连接参数
        connectionFactory.setHost("192.168.31.228");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("demo");
        connectionFactory.setPassword("demo");
        // 3. 创建连接 创建channel
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel();) {
            /*
             * 4. 声明队列
             * 参数:
             * 1. 队列名称
             * 2. 是否持久化
             * 3. 是否排外，是否独占独立
             * 4. 是否自动删除，随着最后一个消费者消息完毕消息以后是否把队列删除
             * 5. 携带附属参数
             */
            channel.queueDeclare("work", true, false, false, null);
            // 5. 发送消息
            for (int i = 0; i < 10; i++) {
                channel.basicPublish("", "work", null, ("hello rabbitmq" + i).getBytes());
            }
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }

    }
}
