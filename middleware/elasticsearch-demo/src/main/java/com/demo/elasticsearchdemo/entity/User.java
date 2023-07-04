package com.demo.elasticsearchdemo.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @className: User
 * @package com.demo.elasticsearchdemo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
@Data
@Accessors(chain = true)
@Document(indexName = "user", shards = 1, replicas = 0)
public class User {
    @Id
    private Long id;
    private String name;
    private Integer age;
    private Department department;
}
