package com.amano.consumer.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.amano.common.entity.ResponseEntity;
import com.amano.producer.api.ProducerApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @className: ConsumerService
 * @package com.amano.consumer.service
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final ProducerApi producerApi;

    @SentinelResource("consumer-hello")
    public ResponseEntity<String> hello(String name) {
        return producerApi.hello(name);
    }

    @SentinelResource("consumer-hello2")
    public ResponseEntity<String> hello2(String name) {
        return producerApi.hello2(name);
    }

    @SentinelResource("consumer-hello3")
    public ResponseEntity<String> hello3(String name) {
        return producerApi.hello3(name);
    }
}
