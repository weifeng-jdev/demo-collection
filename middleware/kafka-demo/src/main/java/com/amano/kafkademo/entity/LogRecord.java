package com.amano.kafkademo.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @className: LogRecord
 * @package com.amano.kafkademo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/3
 **/
@Data
@Accessors(chain = true)
@ToString
public class LogRecord {
    private Long id;

    private String content;

    private Integer operatorId;

    private String operatorName;
}
