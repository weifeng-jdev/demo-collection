package com.amano.rabbitmqdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: RabbitMqConfirmController
 * @package com.amano.rabbitmqdemo.controller
 * @description: Confirm生产者发送消息测试类
 * @author: amano
 * @date: 2023/6/26
 **/
@RestController
@RequestMapping("rabbit/producer/confirm")
@RequiredArgsConstructor
public class RabbitMqConfirmController {
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("send")
    public String send() {
        rabbitTemplate.convertAndSend("non-exchange", "non-routing", "confirm msg");
        return "success";
    }

    @PostMapping("send/nonQueue")
    public String sendNonQueue() {
        rabbitTemplate.convertAndSend("lonelyDirectExchange", "non-routing", "confirm msg");
        return "success";
    }

    @PostMapping("send/success")
    public String send(String msg) {
        rabbitTemplate.convertAndSend("fanoutExchange", null, "msg: fanout" );
        return "success";
    }

    static class LonelyExchangeConfiguration {
        public static final String NAME = "lonelyDirectExchange";

        @Bean
        public DirectExchange lonelyDirectExchange() {
            return new DirectExchange(NAME);
        }

    }
}
