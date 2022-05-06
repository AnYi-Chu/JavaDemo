package springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/thymeleafView")
    public String thymeleafView() {
        return "target";
    }

    @RequestMapping("/forward")
    public String forward() {
        return "forward:/target";
    }

    @RequestMapping("/redirect")
    public String redirect() {
        return "redirect:/target";
    }
}
