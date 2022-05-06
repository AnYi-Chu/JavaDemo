package spring.ioc.xml;

public class Book {
    private String name;
    private Teacher teacher;
    private User user;

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
