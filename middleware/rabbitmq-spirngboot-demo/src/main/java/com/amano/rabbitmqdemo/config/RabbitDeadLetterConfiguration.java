package com.amano.rabbitmqdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RabbitDeadLetterConfiguration
 * @package com.amano.rabbitmqdemo.config
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/23
 **/
@Configuration
@Slf4j
public class RabbitDeadLetterConfiguration {
    /**
     * 创建普通交换机
     */
    @Bean
    public Exchange normalExchange() {
        return ExchangeBuilder.directExchange("normal.exchange").durable(true).build();
    }

    /**
     * 创建普通队列, 设计队列消息过期时间, 绑定死信队列
     */
    @Bean
    public Queue normalQueue() {
        return QueueBuilder.durable("normal.queue")
                .ttl(5000)
                .deadLetterExchange("dead.letter.exchange")
                .deadLetterRoutingKey("dead.letter.routing.key")
                .build();
    }

    /**
     * 创建普通队列和交换机的绑定关系
     */
    @Bean
    public Binding normalBinding(@Qualifier("normalQueue") Queue queue,
                                 @Qualifier("normalExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("normal.routing.key").noargs();
    }

    /**
     * 创建死信交换机
     */
    @Bean
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange("dead.letter.exchange").durable(true).build();
    }

    /**
     * 创建死信队列
     */
    @Bean
    public Queue dealLetterQueue() {
        return QueueBuilder.durable("dead.letter.queue").build();
    }

    /**
     * 创建死信队列和交换机的绑定关系
     */
    @Bean
    public Binding deadLetterBinding(@Qualifier("dealLetterQueue") Queue queue,
                                     @Qualifier("deadLetterExchange") Exchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dead.letter.routing.key").noargs();
    }
}
