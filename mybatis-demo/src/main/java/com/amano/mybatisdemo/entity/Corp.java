package com.amano.mybatisdemo.entity;

import com.amano.mybatisdemo.enums.CropStatusEnum;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @className: Corp
 * @package com.amano.mybatisdemo.entity
 * @description: TODO 类描述
 * @author: amano
 * @date: 2023/6/28
 **/
@Data
// 必须开启autoResultMap = true才能进行自动typeHandler处理
@TableName(value = "t_corp", autoResultMap = true)
@Accessors(chain = true)
public class Corp {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String address;
    private CropStatusEnum status;
    // 演示默认填充字段值
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    /**
     * 使用JacksonTypeHandler对数据库json类型字段做映射处理
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ContactInfo contactInfo;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer deleted;

    /**
     * 联系人信息, 演示jsonTypeHandler
     */
    @Data
    public static class ContactInfo {
        private String name;
        private String phone;
        private String email;
    }
}
