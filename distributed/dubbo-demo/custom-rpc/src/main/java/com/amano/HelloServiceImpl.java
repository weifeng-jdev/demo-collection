package com.amano;

/**
 * @className: HelloServiceImpl
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/12
 **/
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "hello" + name;
    }
}
