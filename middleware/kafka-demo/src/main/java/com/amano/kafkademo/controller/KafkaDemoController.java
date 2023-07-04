package com.amano.kafkademo.controller;

import com.amano.common.entity.ResponseEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @className: KafkaDemoController
 * @package com.amano.kafkademo.controller
 * @description: 通过kafkaAdmin对topic进行增删改查操作
 * @author: weifeng
 * @date: 2023/7/3
 **/
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaDemoController {
    private final AdminClient adminClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 创建topic
     * @param topicName
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createTopic(String topicName) {
        adminClient.createTopics(List.of(new NewTopic(topicName, 1, (short) 1)));
        return ResponseEntity.ok(topicName);
    }

    /**
     * 查询topic详情
     * @param topicName
     * @return
     * @throws JsonProcessingException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @GetMapping
    public ResponseEntity<String> topicDetail(String topicName) throws JsonProcessingException, ExecutionException, InterruptedException {
        DescribeTopicsResult c = adminClient.describeTopics(List.of(topicName));
        TopicDescription topicDescription = c.topicNameValues().get(topicName).get();
        return ResponseEntity.ok(objectMapper.writeValueAsString(topicDescription));
    }

    /**
     * 删除topic
     * @param topicName
     * @return
     */
    @DeleteMapping
    public ResponseEntity<String> deleteTopic(String topicName) {
        adminClient.deleteTopics(List.of(topicName));
        return ResponseEntity.ok(topicName);
    }
}
