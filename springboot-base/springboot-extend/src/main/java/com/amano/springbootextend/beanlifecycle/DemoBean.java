package com.amano.springbootextend.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @className: DemoBean
 * @package com.amano.springbootextend.beanlifecycle
 * @description: Bean生命周期测试类，用于测试Bean生命周期的执行顺序
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
@Slf4j
public class DemoBean implements BeanNameAware, ApplicationContextAware, BeanFactoryAware, InitializingBean, DisposableBean {
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    /**
     * 实现BeanFactoryAware接口, 在BeanFactoryAwareProcessor中执行
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        log.info("==>setBeanFactory exec");
    }

    /**
     * 实现InitializingBean接口, 在InitDestroyAnnotationBeanPostProcessor中执行
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("==>afterPropertiesSet exec");
    }

    /**
     * 实现ApplicationContextAware接口, 在ApplicationContextAwareProcessor中执行
     * @throws Exception
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("==>setApplicationContext exec");
    }

    /**
     * 使用@PostConstruct注解, 在CommonAnnotationBeanPostProcessor中执行
     */
    @PostConstruct
    public void postConstruct() {
        log.info("==>postConstruct exec");
    }

    @PreDestroy
    public void preDestory() {
       log.info("==>preDestory exec");
    }

    @Override
    public void destroy() throws Exception {
        log.info("==>destroy exec");
    }

    @Override
    public void setBeanName(String s) {
        log.info("==>setBeanName exec");
    }
}
