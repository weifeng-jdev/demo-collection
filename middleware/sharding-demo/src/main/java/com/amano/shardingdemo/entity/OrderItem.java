package com.amano.shardingdemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @className: OrderItem
 * @package com.amano.shardingdemo.entity
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
@Data
@TableName("t_order_item")
@Accessors(chain = true)
public class OrderItem {
    private Long id;

    private Long orderId;

    private Integer userId;

    private Integer productId;

    private Integer count;

    private Double amount;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
