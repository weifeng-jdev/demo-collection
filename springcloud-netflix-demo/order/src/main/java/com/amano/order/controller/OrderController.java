package com.amano.order.controller;

import com.amano.common.entity.ResponseEntity;
import com.amano.product.api.ProductApi;
import com.amano.product.api.fallback.ProductApiFallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @className: OrderController
 * @package com.amano.order.controller
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@RestController
@RequestMapping("/api/order")
public class OrderController {
    // 验证配置中中心
    @Value("${config.info}")
    private String config;

    // 验证openfeign和hystrix
    @Autowired
    private ProductApi productApi;

    @GetMapping
    public ResponseEntity<?> listAllOrder() {
        return ResponseEntity.ok(productApi.listAllProduct());
    }
}
