package com.amano.mybatisdemo.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: UserVO
 * @package com.amano.mybatisdemo.vo
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
public class UserVO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
