package com.amano.rabbitmqdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RabbitMqProducerController
 * @package com.amano.rabbitmqdemo.controller
 * @description: 测试rabbitmq生产者发送消息
 * @author: weifeng
 * @date: 2023/6/26
 **/
@RestController
@RequestMapping("rabbit/producer")
@RequiredArgsConstructor
public class RabbitMqProducerController {
    private final AmqpTemplate amqpTemplate;

    @PostMapping("send")
    public String send(String msg) {
        amqpTemplate.convertAndSend("testDirectExchange", "testDirectRouting", "msg:" + msg);
        return "success";
    }
}
