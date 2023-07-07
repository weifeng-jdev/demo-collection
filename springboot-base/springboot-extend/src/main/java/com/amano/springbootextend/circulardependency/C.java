package com.amano.springbootextend.circulardependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @className: C
 * @package com.amano.springbootextend.component.circulardependency
 * @description: c -> a -> b -> c 循环依赖测试类C
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
public class C {
    @Autowired
    private A a;
}
