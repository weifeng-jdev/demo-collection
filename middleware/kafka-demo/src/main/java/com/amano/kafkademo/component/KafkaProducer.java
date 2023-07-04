package com.amano.kafkademo.component;

import com.amano.kafkademo.entity.LogRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.time.LocalDateTime;

/**
 * @className: KafkaProducer
 * @package com.amano.kafkademo.component
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/3
 **/
@Component
@Slf4j
public class KafkaProducer implements ApplicationRunner {
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 模拟发送1000条信息
        for (int i = 0; i < 1000; i++) {
            LogRecord logRecord = new LogRecord();
            logRecord.setId((long) i);
            logRecord.setContent("测试消息" + i);
            logRecord.setOperatorId(1);
            logRecord.setOperatorName("测试用户");
            kafkaTemplate.send("demo-topic", objectMapper.writeValueAsString(logRecord))
                    .addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
                        @Override
                        public void onFailure(Throwable ex) {
                            log.error("send error", ex);
                        }

                        @Override
                        public void onSuccess(SendResult<String, String> result) {
                            log.info("send success: {}", result);
                        }
                    });
        }
        log.info("send seccess");
    }
}
