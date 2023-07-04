package com.amano.rocketmqdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.spring.autoconfigure.RocketMQProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @className: RocketMqConsumerConfiguration
 * @package com.amano.rocketmqdemo.config
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Configuration
@Slf4j
public class RocketMqConsumerConfiguration {
    @Bean
    public DefaultMQPushConsumer consumer(RocketMQProperties properties) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getConsumer().getGroup());
        consumer.setNamesrvAddr(properties.getNameServer());
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            msgs.forEach(msg -> {
                log.info("receive tag:{}", msg.getTags());
                log.info("receive msg:{}", new String(msg.getBody()));
            });
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.subscribe("customize-topic", "test");
        consumer.start();
        return consumer;
    }
}
