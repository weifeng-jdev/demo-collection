package com.amano.shardingdemo;

import com.amano.shardingdemo.entity.Order;
import com.amano.shardingdemo.entity.OrderItem;
import com.amano.shardingdemo.mapper.OrderItemMapper;
import com.amano.shardingdemo.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class ShardingDemoApplicationTests {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;


    @Test
    void contextLoads() {
    }

    @Test
    void testInsertOrder() {
        List<OrderItem> orderItems = List.of(
                new OrderItem().setProductId(1).setUserId(1).setCount(1).setAmount(100.0),
                new OrderItem().setProductId(2).setUserId(1).setCount(1).setAmount(200.0),
                new OrderItem().setProductId(3).setUserId(1).setCount(1).setAmount(300.0),
                new OrderItem().setProductId(4).setUserId(1).setCount(1).setAmount(400.0)
        );
        Order order = new Order()
                .setUserId(1)
                .setOrderItems(orderItems)
                .setAmount(orderItems.stream().reduce(0.0, (sum, item) -> sum + item.getAmount(), Double::sum));
        int r = orderMapper.insert(order);
        log.info("insert order result: {}", r);
        if(r > 0) {
            orderItems.forEach(item -> item.setOrderId(order.getId()));
            int i = orderItemMapper.insertBatch(orderItems);
            log.info("insert order_item result: {}", i);
        }

    }
}
