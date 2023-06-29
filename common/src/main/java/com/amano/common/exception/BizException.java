package com.amano.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @className: BizException
 * @package com.amano.common.exception
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BizException extends RuntimeException{
    private Integer code;
    private String msg;
}
