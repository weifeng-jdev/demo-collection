package com.amano.product.config;

import com.amano.product.api.fallback.ProductApiFallbackFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: ProductApiAutoConfiguration
 * @package com.amano.product.config
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Configuration
public class ProductApiAutoConfiguration {
    @Bean
    public ProductApiFallbackFactory productApiFallbackFactory() {
        return new ProductApiFallbackFactory();
    }
}
