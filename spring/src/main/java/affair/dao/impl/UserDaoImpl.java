package affair.dao.impl;

import affair.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addMoney() {
        String sql = "update account set money=money-? where name?";
        jdbcTemplate.update(sql, 100, "gaohao");
    }

    @Override
    public void reduceMoney() {
        String sql = "update account set money=money+? where name?";
        jdbcTemplate.update(sql, 100, "gaohao");
    }
}
