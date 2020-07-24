## circuitbreak-consumer-example
官方Demo：[spring-cloud-circuitbreaker-demo](https://github.com/spring-cloud-samples/spring-cloud-circuitbreaker-demo)

Hystrix 自带有 HystrixCircuitBreaker -> com.netflix.hystrix.HystrixCircuitBreaker，它是一个接口。

Spring Cloud Hystrix 也有属于自己的 HystrixCircuitBreaker -> org.springframework.cloud.netflix.hystrix.HystrixCircuitBreaker，
它是 org.springframework.cloud.client.circuitbreaker.CircuitBreaker 的实现类。

两者底层都是使用 com.netflix.hystrix.HystrixCommand.Setter 来封装配置数据。

Demo hystrix-consumer-example 用的是 Hystrix 自带的，Demo circuitbreak-consumer-example 是 Spring Cloud Hystrix 自己的。

hystrix-consumer-example 的 CustomConfigurationHelloServiceImpl 上有相关描述了。

circuitbreak-consumer-example 的话如下：
> org.springframework.cloud.client.circuitbreaker.Customizer，它是一个函数式接口，本身也可以封装行为。

首先是 CircuitBreakerGenericConfiguration 初始化了一个 的 Customizer Bean，它的 configureDefault 方法的作用就是初始化一个行为（带配置数据，如：超时2000），
就像初始化（set）一个属性那样，给 HystrixCircuitBreakerFactory 初始化一个 defaultConfiguration 行为。Customizer 行为实现为在这里是调用 configureDefault 方法。

**或者** 

CircuitBreakerSpecificConfiguration 初始化一个 的 Customizer Bean。它的 configure 方法的作用是通过循环执行行为（带配置数据，如：超时2000）的方式将配置数据初始化到 
HystrixCircuitBreakerFactory 的 configurations 中。Customizer 行为实现为在这里是调用 configure 方法。

接着 HystrixCircuitBreakerAutoConfiguration 里边也初始化一个 HystrixCircuitBreakerFactory Bean，这个 Bean 会将上面的行为，
通过遍历的方式封装到 HystrixCircuitBreakerFactory 中。 
> 配置数据放在 org.springframework.cloud.client.circuitbreaker.AbstractCircuitBreakerFactory.configurations 中。

然后到了使用环节了，注入 HystrixCircuitBreakerFactory，调用 create 方法即可，具体使用方式参考 Demo。

