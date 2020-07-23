package com.example.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author Fatal
 * @date 2020/7/23 0023 21:49
 */
@Service
@AllArgsConstructor
public class HttpBinService {

    private RestTemplate restTemplate;

    public Map get() {
        return restTemplate.getForObject("https://httpbin.org/get", Map.class);
    }

    public Map delay(int second) {
        return restTemplate.getForObject("https://httpbin.org/delay/" + second, Map.class);
    }

}
