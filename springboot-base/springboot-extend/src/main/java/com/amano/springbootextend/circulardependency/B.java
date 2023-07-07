package com.amano.springbootextend.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: B
 * @package com.amano.springbootextend.component.circulardependency
 * @description: b -> c -> a -> b循环依赖测试类B
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
public class B {
    @Autowired
    private C c;
}
