package com.amano.rabbitmqdemo.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: DeadLetterConsumer
 * @package com.amano.rabbitmqdemo.listener
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/23
 **/
@Component
@RabbitListener(
        queues = "dead.letter.queue"
)
public class DeadLetterConsumer {
    @RabbitHandler
    public void process(String msg) {
        System.out.println("dead letter queue receive msg:" + msg);
    }
}
