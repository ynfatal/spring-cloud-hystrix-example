package com.example.service.impl;

import com.example.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 测试自定义局部配置 -- 类级别的细粒度
 * 优先级测试结果：方法级别优先级高于类级别
 * @author Fatal
 * @date 2020/7/17 9:21
 */
@Primary
@Service
@AllArgsConstructor
@DefaultProperties(defaultFallback = "defaultFallback")
public class DefaultPropertiesHelloServiceImpl implements IHelloService {

    private RestTemplate restTemplate;

    @Override
    @HystrixCommand // 打开后测试 @DefaultProperties 定义的默认属性
//    @HystrixCommand(fallbackMethod = "helloFallback")   // 打开后测试 @DefaultProperties 和 @HystrixCommand 的优先级
    public String hello() {
        return restTemplate.getForObject("http://HYSTRIX-PROVIDER-EXAMPLE/hello", String.class);
    }

    public String helloFallback() {
        return "helloFallback";
    }

    public String defaultFallback() {
        return "defaultFallback";
    }
}
