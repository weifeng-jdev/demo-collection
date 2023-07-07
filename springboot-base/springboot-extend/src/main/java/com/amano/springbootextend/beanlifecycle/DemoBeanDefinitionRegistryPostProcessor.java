package com.amano.springbootextend.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @className: DemoBeanDefinitionRegistryPostProcessor
 * @package com.amano.springbootextend.beanlifecycle
 * @description: 定义DefinitionRegistryPostProcessor, 此时bean的定义信息 都已经加载完毕 但是还没到实例化以及初始化阶段
 *                 postProcessBeanFactory方法和BeanFactoryPostProcessor方法执行顺序一致
 * @author: weifeng
 * @date: 2023/7/6
 **/
@Component
@Slf4j
public class DemoBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        log.info("==>postProcessBeanDefinitionRegistry exec");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("==>postProcessBeanFactory exec");
    }
}
