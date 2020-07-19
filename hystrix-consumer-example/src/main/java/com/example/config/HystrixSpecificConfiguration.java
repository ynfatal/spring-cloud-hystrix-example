package com.example.config;

import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * HystrixCircuitBreaker 局部配置
 * @author Fatal
 * @date 2020/7/15 8:45
 */
@Configuration(proxyBeanMethods = false)
@Profile(value = "specific_configuration")
public class HystrixSpecificConfiguration {

    /**
     * @apiNote 与提供全局默认配置类似，您可以创建一个自定义 bean，它被传递给 HystrixCircuitBreakerFactory。
     * 单独为 id 为 foo 和 bar 的断路器提供配置数据。
     * @return
     */
    @Bean
    public Customizer<HystrixCircuitBreakerFactory> customizer() {
        return factory -> factory.configure(builder -> builder.commandProperties(
            HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)), "foo", "bar");
    }

}
