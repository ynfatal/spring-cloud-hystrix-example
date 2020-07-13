package com.example.service.impl;

import com.example.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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

    /**
     * Spring Cloud 会将带有 @HystrixCommand 注解的 Spring bean 包装在一个连接到 Hystrix 断路器的代理中.
     * 要配置 @HystrixCommand，可以将 commandProperties 属性或 threadPoolProperties 属性与 @HystrixProperty 注解列表一起使用。请参阅下边链接了解更多详情：
     * https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica#configuration
     * @return
     */
    @HystrixCommand(fallbackMethod = "helloFallback",
        // commandProperties 默认配置可以参考类：com.netflix.hystrix.HystrixCommandProperties
        commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500")
        },
        // threadPoolProperties 默认配置可以参考类：com.netflix.hystrix.HystrixThreadPoolProperties
        threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "30"),
            @HystrixProperty(name = "maxQueueSize", value = "101"),
            @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
            @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
        })
    public String hello2() {
        return restTemplate.getForObject("http://HYSTRIX-PROVIDER-EXAMPLE/hello", String.class);
    }

}
