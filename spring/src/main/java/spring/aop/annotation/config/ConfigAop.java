package spring.aop.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = {"aop.annotation"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {
}
