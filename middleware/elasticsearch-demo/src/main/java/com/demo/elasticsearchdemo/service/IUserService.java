package com.demo.elasticsearchdemo.service;

import com.demo.elasticsearchdemo.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @className: UserService
 * @package com.demo.elasticsearchdemo.service
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
public interface IUserService {
    List<User> listUser();

    Page<User> listPageUser(int pageNum, int pageSize);

    User getUserBydId(long id);

    User saveUser(User user);

    void deleteUser(long id);

    User updateUserById(User user);
}
