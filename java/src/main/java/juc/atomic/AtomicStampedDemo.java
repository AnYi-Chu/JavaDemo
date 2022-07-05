package juc.atomic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicStampedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Book {
    private int id;
    private String bookName;
}

/*
 * AtomicStampedReference拥有版本号，处理修改过几次的问题
 * */
public class AtomicStampedDemo {    //拥有版本号的原子引用
    public static void main(String[] args) {
        Book javaBook = new Book(1, "javaBook");
        AtomicStampedReference<Book> stampedReference = new AtomicStampedReference<>(javaBook, 1);
        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        Book mysqlBook = new Book(2, "mysqlBook");
        boolean b = stampedReference.compareAndSet(javaBook, mysqlBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

        b = stampedReference.compareAndSet(mysqlBook, javaBook, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
    }
}
