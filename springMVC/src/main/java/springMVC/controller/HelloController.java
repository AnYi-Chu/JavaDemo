package springMVC.controller;

import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springMVC.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class HelloController {
    @RequestMapping(value = {"/", "/test"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index() {
        return "index";
    }

    @RequestMapping("/target")
    public String toTarget() {
        return "target";
    }

    @GetMapping("/getTarget")
    public String getTarget() {
        return "target";
    }

    @RequestMapping(value = "/getParamsTarget", params = {"username=admin", "password!=123456"}, headers = {"Host=localhost:8080"})
    public String getParamsTarget() {
        return "target";
    }

    @RequestMapping(value = "/getHeadersTarget", headers = {"Host=localhost:8080"})
    public String getHeadersTarget() {
        return "target";
    }

    @RequestMapping(value = "/getRestTarget/{id}/{username}")
    public String getRestTarget(@PathVariable("id") Integer id, @PathVariable("username") String username) {
        System.out.println(id + username);
        return "target";
    }

    @RequestMapping(value = "/servletAPITarget")
    public String servletAPITarget(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username + password);
        return "target";
    }

    @RequestMapping(value = "/paramTarget")
    public String ParamTarget(@RequestParam(value = "username", required = false, defaultValue = "null") String username, @RequestParam("password") String password, @RequestHeader("Host") String host, @CookieValue("JSESSIONID") String JSESSIONID) {
        System.out.println(username + password + host + JSESSIONID);
        return "target";
    }

    @RequestMapping("/pojoTarget")
    public String pojoTarget(User user) {
        System.out.println(user);
        return "target";
    }

    @RequestMapping("/requestBody")
    public String requestBody(@RequestBody String requestBody) {
        System.out.println(requestBody);
        return "target";
    }

    @RequestMapping("/requestEntity")
    public String requestEntity(RequestEntity requestEntity) {
        //requestEntity表示整个请求报文信息
        System.out.println(requestEntity.getHeaders());
        return "target";
    }

    @RequestMapping("/response")
    public String response(HttpServletResponse response) throws IOException {
        //requestEntity表示整个请求报文信息
        response.getWriter().println("hello world");
        return "target";
    }

    @RequestMapping("/responseBody")
    @ResponseBody
    public User responseBody() {
        return new User("root", "123456");
    }

    @RequestMapping("/ajax")
    @ResponseBody
    public String axios(String username, String password) {
        System.out.println(username + password);
        return "axios,yes";
    }

    @RequestMapping("/interceptor")
    public String interceptor() {
        return "target";
    }

    @RequestMapping("/exceptionHandler")
    public String exceptionHandler() {
        System.out.println(1 / 0);
        return "target";
    }
}
