package com.amano._02_work_queue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @className: WorkQueueProduce
 * @package com.amano._02_work_queue
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/19
 **/
public class WorkQueueConsume01 {
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
        channel.queueDeclare("work", true, false, false, null);
        // 5. 设置每次只能消费一个消息
        channel.basicQos(1);
        // 6. 读取消息
        channel.basicConsume("work", false, new DeliverCallback() {
            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                System.out.println("consumer02: 收到消息：" + new String(delivery.getBody()));
                try {
                    Thread.sleep(3000);
                    channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {
                System.out.println("接收消息失败");
            }
        });
    }
}
