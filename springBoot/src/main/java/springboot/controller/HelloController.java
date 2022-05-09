package springboot.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springboot.bean.Car;
import springboot.bean.Person;

@Slf4j
@RestController
public class HelloController {
//    @Autowired
//    Person person;

//    @RequestMapping("/hello")
//    public String helloWorld() {
//        log.info(String.valueOf(person));
//        return "hello,World";
//    }

//    @Autowired
//    private Car car;
//
//    @RequestMapping("/car")
//    public Car car() {
//        return car;
//    }

    @GetMapping("/user")
    public String getUser() {
        return "getUser";
    }

    @PostMapping("/user")
    public String postUser() {
        return "postUser";
    }

    @PutMapping("/user")
    public String putUser() {
        return "putUser";
    }

    @DeleteMapping("/user")
    public String deleteUser() {
        return "deleteUser";
    }
}
