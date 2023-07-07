package com.amano.springbootextend.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @className: DemoApplicationRunner
 * @package com.amano.springbootextend.runner
 * @description: ApplicationRunner测试类，用于测试Springboot启动时执行的方法，ApplicationRunner和CommandLineRunner的区别是：
 *               ApplicationRunner的run方法的参数是ApplicationArguments，而CommandLineRunner的run方法的参数是String数组
 *               ApplicationArguments可以获取到命令行参数，而String数组只能获取到命令行参数的值
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
@Slf4j
public class DemoApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 打印所有的启动参数键值
        args.getOptionNames().forEach(argKey -> {
            log.info("ApplicationRunner argKey:{}, argValue:{}", argKey, args.getOptionValues(argKey));
        });
    }
}
