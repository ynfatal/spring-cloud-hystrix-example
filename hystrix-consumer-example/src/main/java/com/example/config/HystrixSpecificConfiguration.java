package com.example.config;

import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author Fatal
 * @date 2020/7/15 8:45
 */
@Configuration(proxyBeanMethods = false)
@Profile(value = "specific_configuration")
public class HystrixSpecificConfiguration {

    @Bean
    public Customizer<HystrixCircuitBreakerFactory> customizer() {
        return factory -> factory.configure(builder -> builder.commandProperties(
            HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(2000)), "foo", "bar");
    }

}
