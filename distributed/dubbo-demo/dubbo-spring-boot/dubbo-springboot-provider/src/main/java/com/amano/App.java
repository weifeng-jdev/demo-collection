package com.amano;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: App
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/6
 **/
@SpringBootApplication
@EnableDubbo
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
