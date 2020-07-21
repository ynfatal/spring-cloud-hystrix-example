package com.example.service.impl;

import com.example.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 测试自定义局部配 -- 方法级别的细粒度
 * 官方文档：https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.2.2.RELEASE/reference/html/#netflix-hystrix-starter
 * hystrix 相关属性可以参考类：com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager
 * @implNote 使用 @HystrixCommand 为单个方法添加自定义配置
 * 可以在 com.netflix.hystrix.AbstractCommand#AbstractCommand(com.netflix.hystrix.HystrixCommandGroupKey, com.netflix.hystrix.HystrixCommandKey, com.netflix.hystrix.HystrixThreadPoolKey, com.netflix.hystrix.HystrixCircuitBreaker, com.netflix.hystrix.HystrixThreadPool, com.netflix.hystrix.HystrixCommandProperties.Setter, com.netflix.hystrix.HystrixThreadPoolProperties.Setter, com.netflix.hystrix.HystrixCommandMetrics, com.netflix.hystrix.AbstractCommand.TryableSemaphore, com.netflix.hystrix.AbstractCommand.TryableSemaphore, com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy, com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook)
 * 这里 debug，判断配置是否生效，以及指定的 HystrixCommand 初始化的配置。
 * @author Fatal
 * @date 2020/7/13 0013 22:14
 */
@Primary
@Service
@AllArgsConstructor
public class CustomConfigurationHelloServiceImpl implements IHelloService {

    private RestTemplate restTemplate;

    /**
     * Spring Cloud 会将带有 @HystrixCommand 注解的 Spring bean 包装在一个连接到 Hystrix 断路器的代理中.
     * 要配置 @HystrixCommand，可以将 commandProperties 属性或 threadPoolProperties 属性与 @HystrixProperty 注解列表一起使用。请参阅下边链接了解更多详情：
     * https://github.com/Netflix/Hystrix/tree/master/hystrix-contrib/hystrix-javanica#configuration
     * @return
     */
    @Override
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
    public String hello() {
        return restTemplate.getForObject("http://HYSTRIX-PROVIDER-EXAMPLE/hello", String.class);
    }

    public String helloFallback() {
        return "helloFallback";
    }

}
