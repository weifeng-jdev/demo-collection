package com.amano.springbootextend.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @className: DemoCommandRuner
 * @package com.amano.springbootextend.runner
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/5
 **/
@Component
@Slf4j
public class DemoCommandRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("CommandLineRunner args:{}", args);
    }
}
