package com.amano.rabbitmqdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Slf4j
class RabbitmqDemoApplicationTests {
    @Resource
    private RabbitTemplate rabbitTemplate;
    @Test
    void contextLoads() {
        log.info("contextLoads");
    }

    @Test
    public void testSend() {
        rabbitTemplate.convertAndSend("normal.exchange", "normal.routing.key", "hello rabbitmq");
    }
}
