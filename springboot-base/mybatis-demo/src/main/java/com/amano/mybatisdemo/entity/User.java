package com.amano.mybatisdemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: User
 * @package com.amano.mybatisdemo.entity
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deleted;
}
