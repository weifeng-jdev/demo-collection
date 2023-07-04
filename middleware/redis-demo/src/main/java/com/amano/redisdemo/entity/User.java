package com.amano.redisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @className: User
 * @package com.amano.redisdemo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/29
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class User implements Serializable {
    public static final Long serialVersionUID = 1L;

    private String name;
    private Integer age;
}
