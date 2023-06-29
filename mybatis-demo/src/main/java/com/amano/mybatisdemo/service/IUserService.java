package com.amano.mybatisdemo.service;

import com.amano.mybatisdemo.entity.User;
import com.amano.mybatisdemo.entity.dto.UserCreateDTO;
import com.amano.mybatisdemo.entity.dto.UserPageQuery;
import com.amano.mybatisdemo.entity.dto.UserUpdateDTO;
import com.amano.mybatisdemo.entity.vo.UserVO;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * @className: IUserService
 * @package com.amano.mybatisdemo.service
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
public interface IUserService {
    List<User> listAllUser();

    UserVO getUserById(Long id);

    boolean updateUserById(UserUpdateDTO userVO);

    boolean deleteUserById(Long id);

    UserVO createUser(UserCreateDTO userVO);

    Page<UserVO> listUserPage(UserPageQuery query);
}
