package ioc.xml;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Teacher {
    private String name;
    private List<Object> list;
    private Set<Object> set;
    private Map<Object, Object> map;
    private String[] courses;

    public void setName(String name) {
        this.name = name;
    }

    public void setList(List list) {
        this.list = list;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setCourses(Object[] courses) {
        this.courses = (String[]) courses;
    }
}
