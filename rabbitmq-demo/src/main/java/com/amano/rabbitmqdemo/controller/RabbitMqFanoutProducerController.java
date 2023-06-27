package com.amano.rabbitmqdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RabbitMqFanoutProducerController
 * @package com.amano.rabbitmqdemo.controller
 * @description: 扇形交换机生产者发送消息测试类
 * @author: amano
 * @date: 2023/6/26
 **/
@RestController
@RequestMapping("rabbit/producer/fanout")
@RequiredArgsConstructor
public class RabbitMqFanoutProducerController {
    private final AmqpTemplate amqpTemplate;

    @PostMapping("send")
    public String send(String msg) {
        amqpTemplate.convertAndSend("fanoutExchange", null, "msg: fanout" );
        return "success";
    }
}
