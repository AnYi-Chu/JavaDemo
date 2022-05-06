package springMVC.dao;

import org.springframework.stereotype.Repository;
import springMVC.pojo.Employee;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Repository
public class EmployeeDao {
    Map<Integer, Employee> map = new HashMap<Integer, Employee>() {
        {
            map.put(new Random().nextInt(), new Employee(3, "张三"));
            map.put(new Random().nextInt(), new Employee(4, "李四"));
        }
    };

    public void insert(Employee employee) {
        map.put(new Random().nextInt(), employee);
    }

    public Employee delete(Integer id) {
        return map.remove(id);
    }

    public Collection<Employee> selectAll() {
        return map.values();
    }

    public Employee selectOne(Integer id) {
        return map.get(id);
    }
}
