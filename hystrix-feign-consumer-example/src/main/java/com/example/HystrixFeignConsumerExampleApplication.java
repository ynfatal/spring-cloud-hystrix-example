package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HystrixFeignConsumerExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixFeignConsumerExampleApplication.class, args);
    }

}
