package com.amano.mybatisdemo.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

/**
 * @className: MybatisPlusConfiguration
 * @package com.amano.mybatisdemo.config
 * @description:
 * @author: amano
 * @date: 2023/6/29
 **/
@Configuration
public class MybatisPlusConfiguration {
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        // 设置主键生成策略为雪花算法
        globalConfig.setIdentifierGenerator(new CustomizeIdGenerator());
        return globalConfig;
    }

    public static class CustomizeIdGenerator implements IdentifierGenerator {
        @Override
        public Number nextId(Object entity) {
            // 定义id生成
            return null;
        }
    }

    /**
     * 配置自动字段值填充
     */
    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now())
                        .strictInsertFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
            }
        };
    }
}
