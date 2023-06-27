package com.amano.rocketmqdemo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @className: SimpleListener
 * @package com.amano.rocketmqdemo.listener
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/27
 **/
@Slf4j
public class SimpleListener {
    @Component
    @RocketMQMessageListener(topic = "simple-topic", consumerGroup = "rocket-demo-consumer-group")
    static class SimpleConsumer implements RocketMQListener<String> {

        @Override
        public void onMessage(String message) {
            log.info("receive msg:{}", message);
        }
    }
}
