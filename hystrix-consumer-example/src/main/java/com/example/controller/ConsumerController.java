package com.example.controller;

import com.example.service.IHelloService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fatal
 * @date 2020/7/13 0013 21:49
 */
@RestController
public class ConsumerController {
    @Autowired
    private IHelloService helloService;
    @Autowired(required = false)
    private HystrixCircuitBreakerFactory hystrixCircuitBreakerFactory;
    @Autowired(required = false)
    private Customizer customizer;

    @GetMapping("/consumer")
    public String consumer() {
        return helloService.hello();
    }

}
