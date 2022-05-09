package springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import springboot.bean.Pet;
import springboot.bean.User;
import springboot.config.MyConfig;

@SpringBootApplication  //指明这是一个springboot应用
public class MainApplication {

    public static void main(String[] args) {
        //返回ioc容器
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);

        //查看容器内的组件
//        for (String beanDefinitionName : run.getBeanDefinitionNames()) {
//            System.out.println(beanDefinitionName);
//        }
//
//        System.out.println(run.getBean("tom", Pet.class));
//        System.out.println(run.getBean(MyConfig.class));    //如果@Configuration(proxyBeanMethods = true)，代理对象调用方法
//
//        for (String s : run.getBeanNamesForType(User.class)) {
//            System.out.println(s);
//        }
//
//        System.out.println(run.containsBean("tom"));
    }

}
