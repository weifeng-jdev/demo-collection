package com.amano.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: CodeEnums
 * @package com.amano.common.enums
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Getter
@AllArgsConstructor
public enum CodeEnums {
    SUCCESS(20000, "成功"),
    FAIL(50000, "系统异常"),
    PARAM_ERROR(40000, "参数错误"),
    NOT_FOUND(40400, "资源不存在"),
    UNAUTHORIZED(40100, "未授权"),
    FORBIDDEN(403000, "禁止访问"),
    ;

    private final Integer code;

    private final String msg;
}
