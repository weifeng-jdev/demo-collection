package com.amano.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RabbitFanoutExchangeConfiguration
 * @package com.amano.rabbitmqdemo.config
 * @description: 扇形交换机配置
 * @author: amano
 * @date: 2023/6/26
 **/
@Configuration
public class RabbitFanoutExchangeConfiguration {
    @Bean
    public Queue queueA() {
        return new Queue("fanout.A");
    }

//    @Bean
    public Queue queueB() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue queueC() {
        return new Queue("fanout.C");
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(fanoutExchange());
    }

//    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingC() {
        return BindingBuilder.bind(queueC()).to(fanoutExchange());
    }
}
