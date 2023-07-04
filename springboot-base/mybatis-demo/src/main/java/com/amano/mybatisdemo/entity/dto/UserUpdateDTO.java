package com.amano.mybatisdemo.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @className: UserDTO
 * @package com.amano.mybatisdemo.entity.dto
 * @description: 更新用户dto
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
public class UserUpdateDTO {
    @NotNull(message = "id不能为空")
    private Long id;
    private String email;
    private String phone;
}
