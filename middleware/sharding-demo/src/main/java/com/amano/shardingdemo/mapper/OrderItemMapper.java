package com.amano.shardingdemo.mapper;

import com.amano.shardingdemo.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @className: OrderMapperItem
 * @package com.amano.shardingdemo.mapper
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @Insert(
            "<script>" +
                "insert into t_order_item (id, order_id, count, product_id, amount) values " +
                "<foreach collection='list' item='item' index='index' separator=','>" +
                "(#{item.id}, #{item.orderId}, #{item.count}, #{item.productId}, #{item.amount})" +
                "</foreach>" +
            "</script>"
    )
    int insertBatch(@Param("list") List<OrderItem> orderItemList);
}
