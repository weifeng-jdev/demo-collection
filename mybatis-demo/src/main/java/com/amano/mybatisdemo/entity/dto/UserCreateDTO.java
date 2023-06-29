package com.amano.mybatisdemo.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @className: UserDTO
 * @package com.amano.mybatisdemo.entity.dto
 * @description: 创建用户dto
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
public class UserCreateDTO {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String email;
    private String phone;
}
