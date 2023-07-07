package com.amano.springbootextend.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: A
 * @package com.amano.springbootextend.component.circulardependency
 * @description: a -> b -> c -> a 循环依赖测试类A
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
public class A {
    @Autowired
    private B b;
}
