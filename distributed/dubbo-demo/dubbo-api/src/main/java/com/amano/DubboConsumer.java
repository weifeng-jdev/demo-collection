package com.amano;

import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * @className: DubboConsumer
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/6
 **/
public class DubboConsumer {
    public static void main(String[] args) {
        ReferenceConfig<DemoService> ref = new ReferenceConfig<>();
        ref.setInterface(DemoService.class);

        DubboBootstrap.getInstance()
                .application("dubbo-consumer")
                .registry(new RegistryConfig("zookeeper://localhost:2181"))
                .reference(ref)
                .start();

        DemoService demoService = ref.get();
        String s = demoService.sayHello("zhangsan");
        System.out.println(s);

    }
}
