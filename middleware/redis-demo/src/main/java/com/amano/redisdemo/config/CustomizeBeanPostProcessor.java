package com.amano.redisdemo.config;

import com.amano.redisdemo.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @className: CustomizeBeanPostPorcessor
 * @package com.amano.redisdemo.config
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
@Component
@Slf4j
public class CustomizeBeanPostProcessor implements BeanPostProcessor {
    @Override
    @SuppressWarnings("unchecked")
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (RedisTemplate.class.equals(bean.getClass())) {
            log.info("process redis util");
            RedisUtil.redisTemplate= (RedisTemplate<String, Object>) bean;
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
