package spring.ioc.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

//@Component(value = "userService")   //不写value，bean名称默认类名首字母小写
@Service
public class UserService {
    @Autowired  //根据类型注入
    @Qualifier(value = "userDao")   //根据名称注入，必须和@Autowired一起用
    @Resource(name = "userDao")   //既能类型注入，也能名称注入
    private UserDao userDao;

    @Value(value = "a") //普通注入属性
    private String name;

    public void add() {
        System.out.println("add...");
    }
}
