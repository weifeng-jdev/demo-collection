package com.amano.kafkademo.consumer;

import com.amano.kafkademo.entity.LogRecord;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @className: DemoTopicListener
 * @package com.amano.kafkademo.consumer
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/3
 **/
@Component
@Slf4j
public class DemoTopicListener {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 消费者监听
     *
     * @param consumerRecord 消息体
     * @param ack            消息确认
     * @throws JsonProcessingException json转换异常
     */
    @KafkaListener(topics = "demo-topic", groupId = "kafka-demo")
    public void consumer(ConsumerRecord<String, String> consumerRecord, Acknowledgment ack) throws JsonProcessingException {
        log.info("demo-topic 消费成功, data:{}", (LogRecord) objectMapper.readerFor(LogRecord.class).readValue(consumerRecord.value()));
        // 手动确认消息收到
        ack.acknowledge();
    }
}
