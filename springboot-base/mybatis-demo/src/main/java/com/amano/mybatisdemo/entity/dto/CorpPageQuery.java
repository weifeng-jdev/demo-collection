package com.amano.mybatisdemo.entity.dto;

import lombok.Data;

/**
 * @className: CorpPageQuery
 * @package com.amano.mybatisdemo.entity.dto
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/29
 **/
@Data
public class CorpPageQuery {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String name;
}
