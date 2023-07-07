package com.amano.springbootextend.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: DemoRef
 * @package com.amano.springbootextend.context
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/6
 **/
@Component
public class DemoRef {
    @Autowired
    private Demo demo;
}
