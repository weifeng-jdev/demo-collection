package com.amano.rabbitmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: RabbitMqConsumer
 * @package com.amano.rabbitmqdemo.listener
 * @description: 简单消息消费者
 * @author: weifeng
 * @date: 2023/6/26
 **/
@Component
// 监听的队列
@RabbitListener(queues = "demo-queue")
@Slf4j
public class DirectConsumer {
    @RabbitHandler
    public void process(String msg) {
        log.info("receive msg:" + msg);
    }
}
