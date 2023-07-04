package com.amano.mybatisdemo.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className: UserPageQuery
 * @package com.amano.mybatisdemo.entity.dto
 * @description: 用户分页查询dto
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
public class UserPageQuery {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String username;

    private String email;

    private String phone;

    private LocalDateTime createTimeStart;

    private LocalDateTime createTimeEnd;
}
