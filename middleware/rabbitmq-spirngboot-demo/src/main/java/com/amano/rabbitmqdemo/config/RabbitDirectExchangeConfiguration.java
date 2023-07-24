package com.amano.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RabbitConfiguration
 * @package com.amano.rabbitmqdemo.config
 * @description: rabbitmq队列配置
 * @author: amano
 * @date: 2023/6/26
 **/
@Configuration
public class RabbitDirectExchangeConfiguration {
    /**
     * 声明队列
     */
    @Bean
    public Queue queue() {
        return new Queue("demo-queue");
    }

    /**
     * 声明Direct交换机：testDirectExchange
     * @return
     */
    @Bean
    public DirectExchange testDirectExchange() {
        return new DirectExchange("testDirectExchange");
    }

    /**
     * 绑定队列到交换机，并且指定routingKey
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(testDirectExchange()).with("testDirectRouting");
    }
}
