package com.amano.producer.api;

import com.amano.common.entity.ResponseEntity;
import com.amano.producer.config.ProducerApiFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @className: ProducerApi
 * @package com.amano.producer.api
 * @description: TODO 类描述
 * @author: weifeng
 * @date: 2023/7/1
 **/
@FeignClient(name = "producer", path = "/api/producer")
public interface ProducerApi {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    ResponseEntity<String> hello(@RequestParam("name") String name);

    @RequestMapping(value = "/hello2", method = RequestMethod.GET)
    ResponseEntity<String> hello2(@RequestParam("name") String name);

    @RequestMapping(value = "/hello3", method = RequestMethod.GET)
    ResponseEntity<String> hello3(@RequestParam("name") String name);
}
