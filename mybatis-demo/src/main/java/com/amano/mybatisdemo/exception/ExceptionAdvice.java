package com.amano.mybatisdemo.exception;

import com.amano.common.entity.ResponseEntity;
import com.amano.common.enums.CodeEnums;
import com.amano.common.exception.BizException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

/**
 * @className: ExceptionAdivce
 * @package com.amano.mybatisdemo.exception
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {
    // 处理业务异常，统一返回错误码和错误信息
    @ExceptionHandler(BizException.class)
    public ResponseEntity<?> handleBizException(BizException e) {
        return ResponseEntity.exception(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.exception(CodeEnums.FAIL.getCode(), CodeEnums.FAIL.getMsg());
    }

    //处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return ResponseEntity.exception(CodeEnums.PARAM_ERROR.getCode(), message);
    }

    //处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));
        return ResponseEntity.exception(CodeEnums.PARAM_ERROR.getCode(), message);
    }
}
