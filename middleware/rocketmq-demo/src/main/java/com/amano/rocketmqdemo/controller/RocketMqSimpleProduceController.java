package com.amano.rocketmqdemo.controller;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @className: RocketMqSimpleProduceController
 * @package com.amano.rocketmqdemo.controller
 * @description: 通过mqtempate发送消息
 * @author: amano
 * @date: 2023/6/27
 **/
@RestController
@RequestMapping("rocketmq/producer/simple")
@RequiredArgsConstructor
@Slf4j
public class RocketMqSimpleProduceController {
    private final RocketMQTemplate rocketMQTemplate;
    private final DefaultMQProducer defaultMQProducer;

    @PostMapping("send")
    public SendResult send(String msg) {
        Message<String> message = MessageBuilder.withPayload("msg" + msg).build();
        return rocketMQTemplate.syncSend("simple-topic", message);
    }

    @PostMapping("sendAsync")
    public void sendAsync(String msg) {
        Message<String> message = MessageBuilder.withPayload("msg" + msg).build();
        rocketMQTemplate.asyncSend("simple-topic", message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info(sendResult.toString());
            }

            @Override
            public void onException(Throwable e) {
                log.error("send fail", e);
            }
        });
    }

    @PostMapping("sendOrderly")
    public void sendOrderly(Integer msg) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message<String> message1 = MessageBuilder.withPayload(String.valueOf(msg)).build();
        Message<String> message2 = MessageBuilder.withPayload(String.valueOf(msg +1)).build();
        Message<String> message3 = MessageBuilder.withPayload(String.valueOf(msg +2)).build();
        Message<String> message4 = MessageBuilder.withPayload(String.valueOf(msg +3)).build();
        defaultMQProducer.send(new org.apache.rocketmq.common.message.Message("simple-topic", msg.toString().getBytes()), (mqs, msg1, arg) -> {
            int q = Integer.parseInt(new String(msg1.getBody())) % mqs.size();
            return mqs.get(q);
        }, msg);
//        rocketMQTemplate.syncSendOrderly("simple-topic", message1, message1.getPayload());
//        rocketMQTemplate.syncSendOrderly("simple-topic", message2, message2.getPayload());
//        rocketMQTemplate.syncSendOrderly("simple-topic", message3, message3.getPayload());
//        rocketMQTemplate.syncSendOrderly("simple-topic", message4, message4.getPayload());
    }



}
