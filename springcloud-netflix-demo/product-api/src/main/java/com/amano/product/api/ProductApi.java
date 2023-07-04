package com.amano.product.api;

import com.amano.product.api.fallback.ProductApiFallbackFactory;
import com.amano.product.dto.ProductDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @className: ProductApi
 * @package com.amano.product.api
 * @description: 商品接口
 * @author: weifeng
 * @date: 2023/7/1
 **/
@FeignClient(value = "PRODUCT", path = "/api/product", fallbackFactory = ProductApiFallbackFactory.class)
public interface ProductApi {
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    List<ProductDTO> listAllProduct();
}
