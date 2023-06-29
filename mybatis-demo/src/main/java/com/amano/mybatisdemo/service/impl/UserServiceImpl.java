package com.amano.mybatisdemo.service.impl;

import com.amano.common.enums.CodeEnums;
import com.amano.common.exception.BizException;
import com.amano.mybatisdemo.convert.UserConvertMapper;
import com.amano.mybatisdemo.entity.User;
import com.amano.mybatisdemo.entity.dto.UserCreateDTO;
import com.amano.mybatisdemo.entity.dto.UserPageQuery;
import com.amano.mybatisdemo.entity.dto.UserUpdateDTO;
import com.amano.mybatisdemo.mapper.UserMapper;
import com.amano.mybatisdemo.service.IUserService;
import com.amano.mybatisdemo.entity.vo.UserVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @className: UserServiceImpl
 * @package com.amano.mybatisdemo.service.impl
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserMapper userMapper;

    @Override
    public List<User> listAllUser() {
        return userMapper.listAllUser();
    }

    @Override
    public UserVO getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean updateUserById(UserUpdateDTO userVO) {
        if (Objects.isNull(getUserById(userVO.getId()))) {
            throw new BizException(CodeEnums.NOT_FOUND.getCode(), CodeEnums.NOT_FOUND.getMsg());
        }
        return userMapper.updateUserById(userVO) > 0;
    }

    @Override
    public boolean deleteUserById(Long id) {
        if (Objects.isNull(getUserById(id))) {
            throw new BizException(CodeEnums.NOT_FOUND.getCode(), CodeEnums.NOT_FOUND.getMsg());
        }
        return userMapper.deleteUserById(id) > 0;
    }

    @Override
    public UserVO createUser(UserCreateDTO dto) {
        if(userMapper.countByUsername(dto.getUsername()) > 0) {
            throw new BizException(CodeEnums.PARAM_ERROR.getCode(), "用户名已存在");
        }
        User user = UserConvertMapper.INSTANCE.fromCreateDTO(dto);
        userMapper.createUser(user);
        if (Objects.isNull(user.getId())) {
            throw new BizException(CodeEnums.PARAM_ERROR.getCode(), CodeEnums.PARAM_ERROR.getMsg());
        }
        return UserConvertMapper.INSTANCE.toUserVO(user);
    }

    @Override
    public Page<UserVO> listUserPage(UserPageQuery query) {
        Page<UserVO> result = PageHelper.startPage(query.getPageNum(), query.getPageSize());
        userMapper.listUserPage(query);
        return result;
    }
}
