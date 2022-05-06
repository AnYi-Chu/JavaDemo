import spring.aop.annotation.User;
import spring.aop.xml.Book;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class aopTest {
    @Test
    public void aopAnnotation() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-aop-annotation.xml");
        User user = context.getBean("user", User.class);
        user.add();
    }

    @Test
    public void aopXML() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-aop-xml.xml");
        Book book = context.getBean("book", Book.class);
        book.buy();
    }
}
