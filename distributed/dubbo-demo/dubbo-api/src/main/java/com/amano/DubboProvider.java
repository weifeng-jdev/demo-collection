package com.amano;

import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

/**
 * @className: DubboProvider
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/6
 **/
public class DubboProvider {
    public static void main(String[] args) {
        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(new DemoServiceImpl());

        DubboBootstrap.getInstance()
                .application("dubbo-provider")
                .registry(new RegistryConfig("zookeeper://localhost:2181"))
                .protocol(new ProtocolConfig("dubbo", -1))
                .service(serviceConfig)
                .start()
                .await();
    }
}
