## circuitbreak-consumer-example
官方Demo：[spring-cloud-circuitbreaker-demo](https://github.com/spring-cloud-samples/spring-cloud-circuitbreaker-demo)

Hystrix 自带有 HystrixCircuitBreaker -> com.netflix.hystrix.HystrixCircuitBreaker，它是一个接口。

Spring Cloud Hystrix 也有属于自己的 HystrixCircuitBreaker -> org.springframework.cloud.netflix.hystrix.HystrixCircuitBreaker，
它是 org.springframework.cloud.client.circuitbreaker.CircuitBreaker 的实现类。

两者底层都是使用 com.netflix.hystrix.HystrixCommand.Setter 来封装配置数据。

Demo hystrix-consumer-example 用的是 Hystrix 自带的，Demo circuitbreak-consumer-example 是 Spring Cloud Hystrix 自己的。

hystrix-consumer-example 的 CustomConfigurationHelloServiceImpl 上有相关描述了。

circuitbreak-consumer-example 的话如下：

首先是 CircuitBreakerGenericConfiguration 初始化了一个带行为（configureDefault）的 Bean 或者 
CircuitBreakerSpecificConfiguration 初始化一个带行为（configure）的 Bean。这里的 Bean 是 
org.springframework.cloud.client.circuitbreaker.Customizer 类型的 Bean，两个行为里边都定义了配置数据。

接着 HystrixCircuitBreakerAutoConfiguration 里边也初始化一个 HystrixCircuitBreakerFactory Bean，这个 Bean 会将上面的行为，
通过遍历的方式封装到 HystrixCircuitBreakerFactory 中。 
> 配置数据放在 org.springframework.cloud.client.circuitbreaker.AbstractCircuitBreakerFactory.configurations 中。

然后到了使用环节了，注入 HystrixCircuitBreakerFactory，调用 create 方法即可，具体使用方式参考 Demo。

