package springMVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springMVC.dao.EmployeeDao;
import springMVC.pojo.Employee;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(Employee employee) {
        employeeDao.insert(employee);
        return "redirect:employee";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/selectAll";
    }

    @RequestMapping(value = "/selectAll", method = RequestMethod.GET)
    public String selectAll(Model model) {
        Collection<Employee> employees = employeeDao.selectAll();
        model.addAttribute("employees", employees);
        return "employee";
    }

    @RequestMapping(value = "/selectOne/{id}", method = RequestMethod.GET)
    public String selectOne(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.selectOne(id);
        model.addAttribute("employee", employee);
        return "update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(Employee employee) {
        employeeDao.insert(employee);
        return "redirect:/employee";
    }
}
