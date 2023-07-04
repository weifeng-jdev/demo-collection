package com.amano.mybatisdemo.convert;

import com.amano.mybatisdemo.entity.User;
import com.amano.mybatisdemo.entity.dto.UserCreateDTO;
import com.amano.mybatisdemo.entity.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @className: UserConvertMapper
 * @package com.amano.mybatisdemo.convert
 * @description: mapStruct实现的类型转换映射mapper
 * @author: amano
 * @date: 2023/6/28
 **/
// 标注为mapStruct的映射
@Mapper
public interface UserConvertMapper {
    // 实际负责转换的实现类
    UserConvertMapper INSTANCE = Mappers.getMapper(UserConvertMapper.class);

    UserVO toUserVO(User user);

    @Mapping(ignore = true, target = "id")
    @Mapping(ignore = true, target = "createTime")
    @Mapping(ignore = true, target = "updateTime")
    @Mapping(ignore = true, target = "deleted")
    User fromCreateDTO(UserCreateDTO userCreateDTO);
}
