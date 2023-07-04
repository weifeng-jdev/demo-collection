package com.amano.product.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @className: ProductDTO
 * @package com.amano.product.dto
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@Data
@Accessors(chain = true)
public class ProductDTO {
    private Long id;

    private String name;

    private Double price;
}
