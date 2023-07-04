package com.amano.cloudgateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class CloudGatewayApplication implements InitializingBean {
    @Value("${config.info}")
    private String info;
    @Value("${config.value}")
    private String value;
    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("info:{}, value:{}", info, value);
    }
}
