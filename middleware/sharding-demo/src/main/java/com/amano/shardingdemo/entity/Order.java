package com.amano.shardingdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @className: Order
 * @package com.amano.shardingdemo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
@Data
@TableName("t_order")
@Accessors(chain = true)
public class Order {
    private Long id;

    private Integer userId;

    private Double amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableField(exist = false)
    private List<OrderItem> orderItems;
}
