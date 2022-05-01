import jdbcTemplate.Book;
import jdbcTemplate.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class jdbcTemplateTest {
    @Test
    public void add() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        Book book = new Book();
        book.setId(99L);
        book.setName("学术与政治");
        bookService.add(book);
    }

    @Test
    public void update() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        Book book = new Book();
        book.setId(99L);
        book.setName("君主论");
        bookService.update(book);
    }

    @Test
    public void delete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.delete(99l);
    }

    @Test
    public void countSelect() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.countSelect();
    }

    @Test
    public void selectOne() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.selectOne(1L);
    }

    @Test
    public void selectList() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        bookService.selectList();
    }

    @Test
    public void batchAdd() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {11L, null, null, null, null, null};
        Object[] o2 = {12L, null, null, null, null, null};
        batchArgs.add(o1);
        batchArgs.add(o2);
        bookService.batchAdd(batchArgs);
    }

    @Test
    public void batchUpdate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {"a", 11L};
        Object[] o2 = {"b", 12L};
        batchArgs.add(o1);
        batchArgs.add(o2);
        bookService.batchUpdate(batchArgs);
    }

    @Test
    public void batchDelete() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-jdbcTemplate.xml");
        BookService bookService = context.getBean("bookService", BookService.class);
        List<Object[]> batchArgs = new ArrayList<>();
        Object[] o1 = {11L};
        Object[] o2 = {12L};
        batchArgs.add(o1);
        batchArgs.add(o2);
        bookService.batchDelete(batchArgs);
    }
}
