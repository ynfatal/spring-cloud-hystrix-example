package com.example.controller;

import com.example.service.HttpBinService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Fatal
 * @date 2020/7/23 0023 21:46
 */
@Slf4j
@RestController
@AllArgsConstructor
public class ConsumerController {

    private HttpBinService httpBinService;
    private CircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("/get")
    public Map get() {
        return httpBinService.get();
    }

    /**
     * @implNote <T> T run(Supplier<T> toRun, Function<Throwable, T> fallback)
     * - 参数一：传一个行为，具体要执行的动作
     * - 参数二：传一个行为，包含参数一的服务降级逻辑
     * @param second
     * @return
     */
    @GetMapping("/delay/{second}")
    public Map delay(@PathVariable("second") int second) {
        return circuitBreakerFactory.create("delay").run(() -> httpBinService.delay(second), t -> {
                    log.warn("delay call failed error", t);
                    HashMap<String, String> fallback = new HashMap<>();
                    fallback.put("fallback", "fallback message");
                    return fallback;
                });
    }

}
