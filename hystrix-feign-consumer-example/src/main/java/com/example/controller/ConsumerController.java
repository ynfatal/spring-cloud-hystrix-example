package com.example.controller;

import com.example.feign.IProviderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Fatal
 * @date 2020/7/15 8:05
 */
@RestController
public class ConsumerController {

    @Autowired
    private IProviderClient providerClient;

    @GetMapping("/consumer")
    public String consumer() {
        return providerClient.hello();
    }

}
