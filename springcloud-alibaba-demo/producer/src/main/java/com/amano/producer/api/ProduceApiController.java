package com.amano.producer.api;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.amano.common.entity.ResponseEntity;
import com.amano.common.exception.BizException;
import com.amano.producer.fallback.ProducerApiBlockHandler;
import com.amano.producer.fallback.ProducerApiFallBackHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: ProduceApiController
 * @package com.amano.producer.api
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@RestController
@RequestMapping("/api/producer")
public class ProduceApiController {
    @Value("${config.info}")
    private String info;
    @Value("${config.value}")
    private String value;

    private final AtomicInteger count = new AtomicInteger();

    @GetMapping("/hello")
    @SentinelResource(value = "hello", blockHandlerClass = ProducerApiBlockHandler.class, blockHandler = "hello")
    public ResponseEntity<String> hello(String name) {
        return ResponseEntity.ok("hello:" + name + ",info:" + info + ",value:" + value);
    }

    @GetMapping("/hello2")
    @SentinelResource(
            value = "hello2",
            // 指定流控处理类和方法，只处理blockException，流控优先触发
            blockHandlerClass = ProducerApiBlockHandler.class,
            blockHandler = "hello2",
            // 指定降级处理类和方法，处理所有异常，降级处理在流控处理之后触发
            fallbackClass = ProducerApiFallBackHandler.class,
            fallback = "hello2",
            // 排除对某种异常的降级处理
            exceptionsToIgnore = {BizException.class}
    )
    public ResponseEntity<String> hello2(String name) {
        if (count.incrementAndGet() % 2 == 0) {
            throw new RuntimeException("hello2 error");
        }
        return ResponseEntity.ok("hello:" + name + ",info:" + info + ",value:" + value);
    }

    @GetMapping("/hello3")
    @SentinelResource(
            value = "hello3",
            // 指定流控处理类和方法，只处理blockException，流控优先触发
            blockHandlerClass = ProducerApiBlockHandler.class,
            blockHandler = "hello3",
            // 指定降级处理类和方法，处理所有异常，降级处理在流控处理之后触发
            fallbackClass = ProducerApiFallBackHandler.class,
            defaultFallback = "defaultFallback",
            // 排除对某种异常的降级处理
            exceptionsToIgnore = {BizException.class}
    )
    public ResponseEntity<String> hello3(String name) {
        if (count.incrementAndGet() % 2 == 0) {
            throw new RuntimeException("hello2 error");
        }
        return ResponseEntity.ok("hello:" + name + ",info:" + info + ",value:" + value);
    }
}
