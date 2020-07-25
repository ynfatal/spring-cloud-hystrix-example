# spring-cloud-hystrix-example
[spring-cloud-netflix 2.2.2.RELEASE](https://cloud.spring.io/spring-cloud-static/spring-cloud-netflix/2.2.2.RELEASE/reference/html/#circuit-breaker-spring-cloud-circuit-breaker-with-hystrix)

[Hystrix/Wiki](https://github.com/Netflix/Hystrix/wiki)

### 关于配置优先级

参考资料：Spring Cloud 微服务实战

Hystrix 配置分为四个优先级别：（从上到下，优先级从底到高）

- 全局默认配置（Java）
  > 使用Java语言写的全局默认配置（框架已经写好了）

  参考源码：com.netflix.hystrix.HystrixCommandProperties
- 全局默认配置（yml）
  > 使用全局配置文件如 application.yml 写的全局默认配置（自定义）
  
  参考 Demo：hystrix-consumer-example 的 bootstrap.yml `line11`
- 实例配置（Java）
  > 使用Java语言写的实例配置（自定义）

  参考 Demo：hystrix-consumer-example 的 
  com.example.service.impl.CustomConfigurationHelloServiceImpl.hello（方法级别的细粒度）
  和
  com.example.service.impl.DefaultPropertiesHelloServiceImpl.hello（类级别的细粒度）
- 实例配置（yml）
  > 使用全局配置文件如 application.yml 写的实例配置（自定义）
  
  参考 Demo：hystrix-consumer-example 的 bootstrap.yml `line17`
 