package com.amano.springbootextend.beanlifecycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @className: DemoBeanFactoryPostProcessor
 * @package com.amano.springbootextend.beanlifecycle
 * @description: BeanFactoryPostProcessor 测试类，用于测试BeanFactoryPostProcessor的执行顺序
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
@Slf4j
public class DemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory)
            throws BeansException {
        log.info("==>postProcessBeanFactory exec");
    }
}
