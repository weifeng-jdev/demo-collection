package com.amano.rabbitmqdemo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: TopicConsumer
 * @package com.amano.rabbitmqdemo.listener
 * @description: Topic类型消费者
 * @author: amano
 * @date: 2023/6/26
 **/
public class TopicConsumer {
    @Component
    @RabbitListener(queues = "topic.update")
    static class UpdateConsumer{
        @RabbitHandler
        public void process(String msg) {
            System.out.println("update receive msg:" + msg);
        }
    }

    @Component
    @RabbitListener(queues = "topic.delete")
    static class DeleteConsumer{
        @RabbitHandler
        public void process(String msg) {
            System.out.println("delete receive msg:" + msg);
        }
    }

    @Component
    @RabbitListener(queues = "topic.insert")
    static class InsertConsumer{
        @RabbitHandler
        public void process(String msg) {
            System.out.println("insert receive msg:" + msg);
        }
    }

    @Component
    @RabbitListener(queues = "topic.#")
    static class AllConsumer{
        @RabbitHandler
        public void process(String msg) {
            System.out.println("all receive msg:" + msg);
        }
    }
}
