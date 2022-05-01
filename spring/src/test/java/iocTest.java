import ioc.annotation.UserService;
import ioc.annotation.config.SpirngConfig;
import ioc.xml.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class iocTest {
    @Test
    public void createBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-ioc-xml.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user);
    }

    @Test
    public void createBean2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-ioc-annotation.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
    }

    @Test
    public void createBean3() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpirngConfig.class);
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
    }
}
