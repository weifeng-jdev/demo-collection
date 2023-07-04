package com.amano.mybatisdemo.mapper;

import com.amano.mybatisdemo.entity.User;
import com.amano.mybatisdemo.entity.dto.UserCreateDTO;
import com.amano.mybatisdemo.entity.dto.UserPageQuery;
import com.amano.mybatisdemo.entity.dto.UserUpdateDTO;
import com.amano.mybatisdemo.entity.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @className: UserMapper.xml
 * @package com.amano.mybatisdemo.mapper
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Mapper
public interface UserMapper {
    List<User> listAllUser();

    UserVO getUserById(Long id);

    int updateUserById(@Param("data") UserUpdateDTO userVO);

    /**
     * 使用注解sql语句
     */
    @Update("update t_user set deleted = 1 where id = #{id}")
    int deleteUserById(Long id);

    @Select("select count(*) from t_user where username = #{username}")
    long countByUsername(String username);

    @Insert("insert into t_user (username, password, email, phone) values (#{username}, #{password}, #{email}, #{phone})")
    @SelectKey(keyColumn = "id", keyProperty = "id", resultType = Long.class, before = false, statement = "select last_insert_id()")
    void createUser(User user);

    List<UserVO> listUserPage(UserPageQuery query);
}
