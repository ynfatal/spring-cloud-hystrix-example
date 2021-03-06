package com.example.service.impl;

import com.example.service.IHelloService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author Fatal
 * @date 2020/7/13 0013 22:14
 */
@Service
@Primary
@Slf4j
@AllArgsConstructor
public class HelloServiceImpl implements IHelloService {

    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "helloFallback")
    public String hello() {
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return restTemplate.getForObject("http://HYSTRIX-PROVIDER-EXAMPLE/hello", String.class);
    }

    public String helloFallback() {
        return "helloFallback";
    }

}
