package com.amano.rocketmqdemo;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
@Slf4j
class RocketmqDemoApplicationTests {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void contextLoads() {

    }

    @Test
    void sendCustomize() {
        SendResult sendResult = rocketMQTemplate.syncSend("customize-topic:test", MessageBuilder.withPayload("hello").build());
        log.info("result:{}", sendResult);
    }
}
