package springMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class ScopeController {
    @RequestMapping("/requestByServletAPI")
    public String requestByServletAPI(HttpServletRequest request) {
        request.setAttribute("requestScope", "hello,world");
        return "target";
    }

    @RequestMapping("/requestByModelAndView")
    public ModelAndView requestByModelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("requestScope", "hello,world");
        modelAndView.setViewName("target");
        System.out.println(modelAndView.getClass().getName());
        return modelAndView;
    }

    @RequestMapping("/requestByModelTarget")
    public String requestByModelTarget(Model model) {
        model.addAttribute("requestScope", "hello,world");
        System.out.println(model.getClass().getName());
        return "target";
    }

    @RequestMapping("/requestByMapTarget")
    public String requestByMapTarget(Map<String, Object> map) {
        map.put("requestScope", "hello,world");
        System.out.println(map.getClass().getName());
        return "target";
    }

    @RequestMapping("/requestByModelMapTarget")
    public String requestByModelMapTarget(ModelMap modelMap) {
        modelMap.addAttribute("requestScope", "hello,world");
        return "target";
    }

    @RequestMapping("/sessionByServletAPI")
    public String sessionByServletAPI(HttpSession session) {
        session.setAttribute("sessionScope", "hello,world");
        return "target";
    }

    @RequestMapping("/applicationByServletAPI")
    public String applicationByServletAPI(HttpSession session) {
        ServletContext application = session.getServletContext();
        application.setAttribute("applicationScope", "hello,world");
        return "target";
    }
}
