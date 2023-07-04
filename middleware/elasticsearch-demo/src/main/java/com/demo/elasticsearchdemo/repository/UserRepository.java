package com.demo.elasticsearchdemo.repository;

import com.demo.elasticsearchdemo.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @className: repository
 * @package com.demo.elasticsearchdemo
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
@Repository
public interface UserRepository extends ElasticsearchRepository<User, Long> {
}
