package com.amano.product.controller;

import com.amano.product.api.ProductApi;
import com.amano.product.dto.ProductDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy.THREAD;
import static com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager.*;

/**
 * @className: ProductApiController
 * @package com.amano.product.controller
 * @description: 商品接口实现
 * @author: weifeng
 * @date: 2023/7/1
 **/
@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    private List<ProductDTO> productDTOS = List.of(
            new ProductDTO().setId(1L).setName("aaaa").setPrice(100.0),
            new ProductDTO().setId(2L).setName("bbbb").setPrice(100.0),
            new ProductDTO().setId(3L).setName("cccc").setPrice(100.0),
            new ProductDTO().setId(4L).setName("dddd").setPrice(100.0)
    );

    @GetMapping("/list")
//    @HystrixCommand(
//            fallbackMethod = "listAllProductFallback",
//            commandProperties = {
//                    @HystrixProperty(name = EXECUTION_ISOLATION_STRATEGY, value = "THREAD"),
//                    @HystrixProperty(name = EXECUTION_ISOLATION_THREAD_TIMEOUT_IN_MILLISECONDS, value = "1000")
//            },
//            threadPoolProperties = {
//                    @HystrixProperty(name = CORE_SIZE, value = "20"),
//                    @HystrixProperty(name = MAX_QUEUE_SIZE, value = "1000")
//            }
//    )
    public List<ProductDTO> listAllProduct() throws InterruptedException {
        Thread.sleep(2000);
        return productDTOS;
    }

    public List<ProductDTO> listAllProductFallback() {
        return Collections.emptyList();
    }
}
