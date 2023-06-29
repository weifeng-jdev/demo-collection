package com.amano.mybatisdemo.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @className: CropStatusEnum
 * @package com.amano.mybatisdemo.enums
 * @description: 租户状态枚举，演示数据库自动枚举映射
 * @author: amano
 * @date: 2023/6/29
 **/
@Getter
@AllArgsConstructor
public enum CropStatusEnum implements IEnum<Integer> {
    UNABLE(0, "无效"),
    NORMAL(1, "正常");

    @Override
    public String toString() {
        return descp;
    }

    private final Integer value;
    private final String descp;

    @Override
    public Integer getValue() {
        return value;
    }
}
