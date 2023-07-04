package com.amano.producer.fallback;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.amano.common.entity.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @className: ProducerApiBlockHandler
 * @package com.amano.producer.fallback
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
@Slf4j
public class ProducerApiBlockHandler {

    /**
     * 流控处理，方法必须为public static，返回值必须与原方法一致，参数与原方法一致，并且在最后添加一个BlockException的参数
     */
    public static ResponseEntity<String> hello(String name, BlockException e) {
        log.error("blcoked", e);
        return ResponseEntity.ok("sorry I'm blocked! " + name);
    }
}
