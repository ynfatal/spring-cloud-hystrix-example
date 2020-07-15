package com.example.feign.fallback;

import com.example.feign.IProviderClient;
import org.springframework.stereotype.Component;

/**
 * @author Fatal
 * @date 2020/7/15 8:08
 */
@Component
public class ProviderClientFallback implements IProviderClient {

    @Override
    public String hello() {
        return "feign -> fallback";
    }
}
