package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Fatal
 * @date 2020/7/15 8:07
 */
@FeignClient(value = "HYSTRIX-PROVIDER-EXAMPLE")
public interface IProviderClient {

    @GetMapping("/hello")
    String hello();

}
