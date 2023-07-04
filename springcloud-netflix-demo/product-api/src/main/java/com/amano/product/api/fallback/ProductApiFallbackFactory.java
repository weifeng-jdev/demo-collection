package com.amano.product.api.fallback;

import com.amano.product.api.ProductApi;
import com.amano.product.dto.ProductDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @className: ProductApiFallbackFactory
 * @package com.amano.product.api.fallback
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
public class ProductApiFallbackFactory implements FallbackFactory<ProductApi> {
    @Override
    public ProductApi create(Throwable cause) {
        return new ProductApiFallback(cause);
    }

    public static class ProductApiFallback implements ProductApi{
        private final Throwable cause;

        public ProductApiFallback(Throwable cause) {
            this.cause = cause;
        }

        @Override
        public List<ProductDTO> listAllProduct() {
            return Collections.emptyList();
        }
    }
}
