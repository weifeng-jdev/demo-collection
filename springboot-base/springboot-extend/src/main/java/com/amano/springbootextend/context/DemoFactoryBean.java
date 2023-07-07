package com.amano.springbootextend.context;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @className: DemoFactoryBean
 * @package com.amano.springbootextend.context
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/6
 **/
@Component
@Slf4j
public class DemoFactoryBean implements FactoryBean<Demo>{
    private ApplicationContext applicationContext;
    @Override
    public Demo getObject() throws Exception {
        log.info("==>DemoFactoryBean.getObject");
        Demo demo = new Demo();
        return demo;
    }

    @Override
    public Class<?> getObjectType() {
        return Demo.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
