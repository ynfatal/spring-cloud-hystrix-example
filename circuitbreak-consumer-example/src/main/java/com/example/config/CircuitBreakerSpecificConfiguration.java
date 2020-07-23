package com.example.config;

import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * CircuitBreaker 局部配置
 * 局限于使用 org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory#create(java.lang.String) 方法创建的断路器
 * @author Fatal
 * @date 2020/7/24 5:06
 */
@Configuration(proxyBeanMethods = false)
@Profile(value = {"circuitbreaker_specific_configuration", "default"})
public class CircuitBreakerSpecificConfiguration {

    /**
     * @apiNote 与提供通用配置类似，您可以创建一个自定义 bean，它被传递给 HystrixCircuitBreakerFactory。
     * 单独为 id 为 foo 、bar 和 delay 的断路器提供配置数据。
     * @return
     */
    @Bean
    public Customizer<HystrixCircuitBreakerFactory> specificCustomizer() {
        return factory -> factory.configure(builder -> builder.commandProperties(
            HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000)), "foo", "bar", "delay");
    }

}
