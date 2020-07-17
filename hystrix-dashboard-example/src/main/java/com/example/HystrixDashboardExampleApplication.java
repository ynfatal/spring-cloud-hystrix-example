package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 问题：Unable to connect to Command Metric Stream.
 * 你监控的应用没有引入 actuator 功能。可以参考官网：https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.2.2.RELEASE/reference/html/#hystrix-metrics-stream
 */
@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
public class HystrixDashboardExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardExampleApplication.class, args);
    }

}
