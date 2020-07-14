package com.example.controller;

import com.example.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/consumer")
    public String consumer() {
        return helloService.hello();
    }

}
