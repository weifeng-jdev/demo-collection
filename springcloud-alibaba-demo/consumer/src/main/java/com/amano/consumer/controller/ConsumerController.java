package com.amano.consumer.controller;

import com.amano.common.entity.ResponseEntity;
import com.amano.consumer.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ConsumerController
 * @package com.amano.consumer.controller
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@RestController
@RequestMapping("/api/consumer")
@RequiredArgsConstructor
public class ConsumerController {
   private final ConsumerService consumerService;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(String name) {
        return consumerService.hello(name);
    }

    @GetMapping("/hello2")
    public ResponseEntity<String> hello2(String name) {
        return consumerService.hello2(name);
    }

    @GetMapping("/hello3")
    public ResponseEntity<String> hello3(String name) {
        return consumerService.hello3(name);
    }
}
