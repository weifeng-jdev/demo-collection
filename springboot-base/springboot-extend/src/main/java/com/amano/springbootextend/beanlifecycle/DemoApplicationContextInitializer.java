package com.amano.springbootextend.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @className: DemoApplicationContextInitializer
 * @package com.amano.springbootextend.beanlifecycle
 * @description: 定义spring容器初始化器,在META-INF/spring.factorise中配置加载
 * @author: weifeng
 * @date: 2023/7/6
 **/
@Slf4j
public class DemoApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("==>DemoApplicationContextInitializer initialize");
    }
}
