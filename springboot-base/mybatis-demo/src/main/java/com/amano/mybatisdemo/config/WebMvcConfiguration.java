package com.amano.mybatisdemo.config;

import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: WebMvcConfiguration
 * @package com.amano.mybatisdemo.config
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/29
 **/
@Configuration
public class WebMvcConfiguration {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer(){
        return builder -> builder.featuresToEnable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
    }
}
