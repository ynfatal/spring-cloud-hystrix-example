package com.example.service.impl;

import com.example.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 官方文档：https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.2.2.RELEASE/reference/html/#netflix-hystrix-starter
 * hystrix 相关属性可以参考类：com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager
 * @author Fatal
 * @date 2020/7/13 0013 22:14
 */
@Service
@Slf4j
@AllArgsConstructor
public class HelloServiceImpl implements IHelloService {

    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {
        return restTemplate.getForObject("http://HYSTRIX-PROVIDER-EXAMPLE/hello", String.class);
    }

    public String helloFallback() {
        return "helloFallback";
    }


}
