package spring.jdbcTemplate;

import java.util.List;

public interface BookDao {
    void add(Book book);

    void update(Book book);

    void delete(Long id);

    void countSelect();

    Book selectOne(Long id);

    List<Book> selectList();

    void batchAdd(List<Object[]> batchArgs);

    void batchUpdate(List<Object[]> batchArgs);

    void batchDelete(List<Object[]> batchArgs);
}
