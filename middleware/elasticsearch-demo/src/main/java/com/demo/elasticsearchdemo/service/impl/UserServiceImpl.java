package com.demo.elasticsearchdemo.service.impl;

import com.demo.elasticsearchdemo.entity.User;
import com.demo.elasticsearchdemo.repository.UserRepository;
import com.demo.elasticsearchdemo.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: UserServiceImpl
 * @package com.demo.elasticsearchdemo.service.impl
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/30
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    @Override
    public List<User> listUser() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public Page<User> listPageUser(int pageNum, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNum, pageSize, Sort.by("id"));
        Page<User> all = userRepository.findAll(pageRequest);
        return all;
    }

    @Override
    public User getUserBydId(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(User user) {
        return userRepository.save(user);
    }
}
