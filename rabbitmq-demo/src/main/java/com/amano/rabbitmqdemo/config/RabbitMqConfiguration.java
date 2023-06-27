package com.amano.rabbitmqdemo.config;

import com.amano.rabbitmqdemo.listener.AckConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RabbitMqConfiguration
 * @package com.amano.rabbitmqdemo.config
 * @description: RabbitMq全局配置
 * @author: amano
 * @date: 2023/6/26
 **/
@Configuration
@Slf4j
public class RabbitMqConfiguration {
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        // 强制调用回调函数
        rabbitTemplate.setMandatory(true);
        // 设置消息确认回调函数
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("confirm callback correlationData:" + correlationData);
            log.info("confirm callback ack:" + ack);
            log.info("confirm callback cause:" + cause);
        });
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("confirm callback message:" + message);
            log.info("confirm callback replyCode:" + replyCode);
            log.info("confirm callback replyText:" + replyText);
            log.info("confirm callback exchange:" + exchange);
            log.info("confirm callback routingKey:" + routingKey);
        });
        return rabbitTemplate;
    }

    @Autowired
    private CachingConnectionFactory cachingConnectionFactory;
    @Autowired
    private AckConsumer ackConsumer;

    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        // 设置监听的队列
        container.setQueueNames("fanout.B");
        // 其他监听队列的方式
//         container.setQueueNames("fanout.A", "fanout.B", "fanout.C");
        // 直接创建队列进行监听
//        container.addQueues(new Queue("fanout.A"));
//        container.addQueues(new Queue("fanout.B"));
//        container.addQueues(new Queue("fanout.C"));
        // 设置消费者数量
        container.setConcurrentConsumers(1);
        // 设置消息确认模式
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        // 设置消息监听者
        container.setMessageListener(ackConsumer);
        return container;
    }
}
