package spring.ioc.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration  //标志为配置类
@ComponentScan(basePackages = "ioc")    //组件扫描
public class SpirngConfig {
}
