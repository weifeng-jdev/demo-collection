package com.amano.springbootextend.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @className: DemoBeanPostProprocessor
 * @package com.amano.springbootextend.beanlifecycle
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
@Slf4j
public class DemoBeanPostProprocessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(DemoBean.class)) {
            log.info("==>postProcessBeforeInitialization.postProcessBeforeInitialization exec");
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().equals(DemoBean.class)) {
            log.info("==>postProcessBeforeInitialization.postProcessAfterInitialization exec");
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
