package com.amano.rabbitmqdemo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: FanoutConsumer
 * @package com.amano.rabbitmqdemo.listener
 * @description: 扇形交换机消费者监听类
 * @author: amano
 * @date: 2023/6/26
 **/
public class FanoutConsumer {
    @Component
    @RabbitListener(queues = "fanout.A")
    static class FanoutConsumerA {
        @RabbitHandler
        public void listen(String msg) {
            System.out.println("fanoutConsumerA listen msg:" + msg);
        }
    }

    @Component
    @RabbitListener(queues = "fanout.B")
    static class FanoutConsumerB {
        @RabbitHandler
        public void listen(String msg) {
            System.out.println("fanoutConsumerB listen msg:" + msg);
        }
    }

    @Component
    @RabbitListener(queues = "fanout.C")
    static class FanoutConsumerC {
        @RabbitHandler
        public void listen(String msg) {
            System.out.println("fanoutConsumerC listen msg:" + msg);
        }
    }
}
