package com.demo.elasticsearchdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @className: Department
 * @package com.demo.elasticsearchdemo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
@Data
@Accessors(chain = true)
public class Department {
    private Long id;

    private String name;

    private Long pId;

    private String desc;
}
