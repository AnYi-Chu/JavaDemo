package jdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void add(Book book) {
        bookDao.add(book);
    }

    public void update(Book book) {
        bookDao.update(book);
    }

    public void delete(Long id) {
        bookDao.delete(id);
    }

    public void countSelect() {
        bookDao.countSelect();
    }

    public Book selectOne(Long id) {
        Book book = bookDao.selectOne(id);
        return book;
    }

    public List<Book> selectList() {
        List<Book> books = bookDao.selectList();
        return books;
    }

    public void batchAdd(List<Object[]> batchArgs) {
        bookDao.batchAdd(batchArgs);
    }

    public void batchUpdate(List<Object[]> batchArgs) {
        bookDao.batchUpdate(batchArgs);
    }

    public void batchDelete(List<Object[]> batchArgs) {
        bookDao.batchDelete(batchArgs);
    }
}
