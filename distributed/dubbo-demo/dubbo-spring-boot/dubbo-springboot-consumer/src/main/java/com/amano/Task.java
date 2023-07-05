package com.amano;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @className: Task
 * @package com.amano
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/6/6
 **/
@Component
public class Task implements CommandLineRunner {
    @DubboReference
    private DemoService demoService;
    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("============>" + demoService.sayHello("weifeng"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
