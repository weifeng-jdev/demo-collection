package com.amano.common.entity;

import com.amano.common.enums.CodeEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: ResponseEntity
 * @package com.amano.common.entity
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private Integer code;

    private String msg;

    private T data;

    public static <T> ResponseEntity<T> ok() {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(CodeEnums.SUCCESS.getCode());
        entity.setMsg(CodeEnums.SUCCESS.getMsg());
        return entity;
    }

    public static <T> ResponseEntity<T> ok(T data) {
        ResponseEntity<T> entity = new ResponseEntity<T>();
        entity.setCode(CodeEnums.SUCCESS.getCode());
        entity.setMsg(CodeEnums.SUCCESS.getMsg());
        entity.setData(data);
        return entity;
    }

    public static <T> ResponseEntity<T> exception(Integer code, String msg) {
        ResponseEntity<T> entity = new ResponseEntity<>();
        entity.setCode(code);
        entity.setMsg(msg);
        return entity;
    }
}
