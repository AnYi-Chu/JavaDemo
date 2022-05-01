package affair.service;

import affair.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.REPEATABLE_READ,
        timeout = 1,
        readOnly = true,
        rollbackFor = Exception.class)
public class UserService {
    @Autowired
    private UserDao userDao;

    //转账
    public void accountMoney() {
        try {
            //开启事务
            //业务操作
            userDao.addMoney();
            userDao.reduceMoney();
        } catch (Exception e) {
            //事务回滚
        }

    }
}
