package com.amano.rabbitmqdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RabbitMqTopicProducerController
 * @package com.amano.rabbitmqdemo.controller
 * @description: Topic交换机生产者发送消息测试类
 * @author: weifeng
 * @date: 2023/6/26
 **/
@RestController
@RequestMapping("rabbit/producer/topic")
@RequiredArgsConstructor
public class RabbitMqTopicProducerController {
    private final AmqpTemplate amqpTemplate;

    @PostMapping("update")
    public String updateMsg() {
        amqpTemplate.convertAndSend("topicExchange", "topic.update", "update msg");
        return "success";
    }

    @PostMapping("delete")
    public String deleteMsg() {
        amqpTemplate.convertAndSend("topicExchange", "topic.delete", "delete msg");
        return "success";
    }

    @PostMapping("insert")
    public String insertMsg() {
        amqpTemplate.convertAndSend("topicExchange", "topic.insert", "insert msg");
        return "success";
    }
}
