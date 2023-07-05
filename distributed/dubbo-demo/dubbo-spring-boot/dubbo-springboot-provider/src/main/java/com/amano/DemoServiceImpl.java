package com.amano;

import org.apache.dubbo.config.annotation.DubboService;

/**
 * @className: DemoServiceImpl
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/6
 **/
@DubboService
public class DemoServiceImpl implements DemoService{
    @Override
    public String sayHello(String name) {
        return "hello" + name;
    }
}
