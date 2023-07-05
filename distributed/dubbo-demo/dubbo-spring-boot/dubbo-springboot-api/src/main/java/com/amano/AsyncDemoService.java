package com.amano;

import java.util.concurrent.CompletableFuture;

/**
 * @className: AsyncDemoService
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/6
 **/
public interface AsyncDemoService {
    String sayHello(String name);

    default String sayHello2(String name) {
        return "hello2 " + name;
    }

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }
}
