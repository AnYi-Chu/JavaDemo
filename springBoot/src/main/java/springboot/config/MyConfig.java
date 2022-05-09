package springboot.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import springboot.bean.Car;
import springboot.bean.Pet;
import springboot.bean.User;
//@EnableConfigurationProperties(Car.class)   //开启属性配置，将组件注入容器内
//@ImportResource("classpath:bean.xml")   //导入配置文件
//@ConditionalOnBean(name = "tom")
//@ConditionalOnMissingBean(name = "tom")
//@Import({User.class})   //为容器自动创建组件，组件名默认为全类名
/*
 * 标志为配置类，它也是组件
 * proxyBeanMethods=true，即full模式，容器会检查
 * proxyBeanMethods=false，即lite模式，容器会跳过检测
 */
//@Configuration(proxyBeanMethods = false)
public class MyConfig {

//    @Bean   //向容器内添加组件，默认单例，以方法名作为组件id，返回类型就是组件类型，返回值就是组件在容器中的实例
//    public User newUser() {
//        return new User("a", 18, new Pet("tom"));
//    }
//
//    @Bean("tom")
//    public Pet newPet() {
//        return new Pet("b");
//    }
}
