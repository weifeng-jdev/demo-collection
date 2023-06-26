package com.amano.rabbitmqdemo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: RabbitTopicExchangeConfiguration
 * @package com.amano.rabbitmqdemo.config
 * @description: Topic类型的交换机配置
 * @author: weifeng
 * @date: 2023/6/26
 **/
@Configuration
public class RabbitTopicExchangeConfiguration {
    public final static String TOPIC_EXCHANGE_NAME_UPDATE = "topic.update";
    public final static String TOPIC_EXCHANGE_NAME_DELETE = "topic.delete";
    public final static String TOPIC_EXCHANGE_NAME_INSERT = "topic.insert";
    public final static String TOPIC_EXCHANGE_NAME_ALL = "topic.#";

    @Bean
    public Queue updateQueue() {
        return new Queue(TOPIC_EXCHANGE_NAME_UPDATE);
    }

    @Bean
    public Queue deleteQueue() {
        return new Queue(TOPIC_EXCHANGE_NAME_DELETE);
    }

    @Bean
    public Queue insertQueue() {
        return new Queue(TOPIC_EXCHANGE_NAME_INSERT);
    }

    /**
     * 声明队列，所有以topic.开头的信息都会被接收
     */
    @Bean
    public Queue allQueue() {
        return new Queue(TOPIC_EXCHANGE_NAME_ALL);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding updateBinding() {
        return BindingBuilder.bind(updateQueue()).to(topicExchange()).with(TOPIC_EXCHANGE_NAME_UPDATE);
    }
    @Bean
    public Binding deleteBinding() {
        return BindingBuilder.bind(deleteQueue()).to(topicExchange()).with(TOPIC_EXCHANGE_NAME_UPDATE);
    }
    @Bean
    public Binding insertBinding() {
        return BindingBuilder.bind(insertQueue()).to(topicExchange()).with(TOPIC_EXCHANGE_NAME_UPDATE);
    }

    /**
     * 绑定队列到交换机，并且指定routingKey，#表示0个或多个单词，*表示一个单词
     */
    @Bean
    public Binding AllBinding() {
        return BindingBuilder.bind(allQueue()).to(topicExchange()).with(TOPIC_EXCHANGE_NAME_ALL);
    }
}
