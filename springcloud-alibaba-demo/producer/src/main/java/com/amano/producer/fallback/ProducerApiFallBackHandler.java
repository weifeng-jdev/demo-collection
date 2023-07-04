package com.amano.producer.fallback;

import com.amano.common.entity.ResponseEntity;

/**
 * @className: ProducerApiFallBackHandler
 * @package com.amano.producer.fallback
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/2
 **/
public class ProducerApiFallBackHandler {

    /**
     * 异常降级处理，方法必须为public static，返回值必须与原方法一致，参数与原方法一致，可以增加一个Throwable
     */
    public static ResponseEntity<String> hello2(String name, Throwable e) {
        return ResponseEntity.ok("sorry I'm fallback! " + name);
    }

    /**
     * 默认降级处理方法，方法必须为public static，返回值与原函数一致，参数列表必须为空，可以增加一个Throwable
     * 优先级低于制定的fallback方法
     */
    public static ResponseEntity<String> defaultFallback(Throwable e) {
        return ResponseEntity.ok("sorry I'm fallback! default");
    }
}
