package spring.ioc.xml;

public class User {
    private String name;
    private String account;
    private String sex;
    private String husband;
    private Book book;

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public User(String name) {
        this.name = name;
    }

    public void setHusband(String husband) {
        this.husband = husband;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

}
