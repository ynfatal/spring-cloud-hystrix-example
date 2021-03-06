package com.example.config;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * CircuitBreaker 通用配置
 * 局限于使用 org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory#create(java.lang.String) 方法创建的断路器
 * 可以通过 debug 这个 create 方法来查看配置是否生效
 * @author Fatal
 * @date 2020/7/23 0023 22:00
 */
@Configuration(proxyBeanMethods = false)
@Profile(value = {"circuitbreaker_generic_configuration", "default"})
public class CircuitBreakerGenericConfiguration {

    /**
     * @apiNote 为所有断路器提供默认配置，该 bean 将传递给 HystrixCircuitBreakerFactory。具体可以参考自动配置类源码：
     * org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerAutoConfiguration，可以理解为行为嵌套封装。
     * configureDefault 方法可以用于提供默认配置，它真正起到的作用是将行为 defaultConfiguration 封装到 HystrixCircuitBreakerFactory 中。
     * @return
     */
    @Bean
    public Customizer<HystrixCircuitBreakerFactory> genericCustomizer() {
        return factory -> factory.configureDefault(id -> HystrixCommand.Setter
            .withGroupKey(HystrixCommandGroupKey.Factory.asKey(id))
            .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)));
    }

}
