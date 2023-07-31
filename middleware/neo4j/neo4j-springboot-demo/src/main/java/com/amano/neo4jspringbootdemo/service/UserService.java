package com.amano.neo4jspringbootdemo.service;

import com.amano.neo4jspringbootdemo.entity.UserNode;
import com.amano.neo4jspringbootdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @className: UserSservice
 * @package com.amano.neo4jspringbootdemo.service
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/7/30
 **/
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserNode createUserNode(UserNode user) {
        return userRepository.save(user);
    }

    public boolean deleteUserById(long id) {
        userRepository.deleteById(id);
        return true;
    }

    public UserNode getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUserById(UserNode userNode) {
        userRepository.save(userNode);
    }

    public UserNode getUserByName(String name) {
        return userRepository.findByUname(name);
    }

    public UserNode findFriend(String u1, String u2) {
        return userRepository.findFriend(u1, u2);
    }
}
