package com.amano.jpademo;

import com.alibaba.fastjson2.JSONObject;
import com.amano.jpademo.dao.UserRepository;
import com.amano.jpademo.entity.Department;
import com.amano.jpademo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
class JpaDemoApplicationTests {
    @Autowired
    UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void listAllUser() {
        List<User> all = userRepository.findAll();
        log.info("all users:{}", JSONObject.toJSONString(all));
    }

    @Test
    void testGetOneById() {
        Optional<User> user = userRepository.findById(1L);
        log.info("one user:{}", user.map(JSONObject::toJSONString).orElse(null));
        Optional<User> user2 = userRepository.findById(2L);
        log.info("one user:{}", user2.map(JSONObject::toJSONString).orElse(null));
    }

    @Test
    void testCreateUser() {
        User normal = userRepository.save(new User().setName("normal").setDepartment(new Department().setId(2L)));
        log.info("normal user:{}", normal);
    }

    @Test
    void testCreateUserCascade() {
        User normal = userRepository.save(new User().setName("normal").setDepartment(new Department().setName("test2").setParentId(1L)));
        log.info("normal user:{}", normal);
    }

    @Test
    void testUpdateUser() {
        Optional<User> user2 = userRepository.findById(2L);
        log.info("one user:{}", user2.map(JSONObject::toJSONString).orElse(null));
        assert user2.isPresent();
        User user = user2.get();
        user.setName("normal_new");
        User newData = userRepository.saveAndFlush(user);
        log.info("new user:{}", newData);
    }

    @Test
    void testDeleteUser() {
        userRepository.deleteById(2L);
        // 无数据会抛出异常
        userRepository.deleteById(2L);
    }
}
