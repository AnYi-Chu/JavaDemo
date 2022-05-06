import spring.affair.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class affairTest {
    @Test
    public void account() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-affair.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.accountMoney();
    }
}
