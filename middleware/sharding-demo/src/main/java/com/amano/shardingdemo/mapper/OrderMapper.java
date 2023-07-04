package com.amano.shardingdemo.mapper;

import com.amano.shardingdemo.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @className: OrderMapper
 * @package com.amano.shardingdemo.mapper
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
