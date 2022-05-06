package springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll() {
        System.out.println("查询所有");
        return "target";
    }

    @RequestMapping(value = "/user{id}", method = RequestMethod.GET)
    public String getById() {
        System.out.println("查询指定");
        return "target";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String add(String username, String password) {
        System.out.println("添加" + username + password);
        return "target";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String update(String username, String password) {
        System.out.println("修改" + username + password);
        return "target";
    }
}
