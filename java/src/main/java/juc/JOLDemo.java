package juc;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JOLDemo {
    public static void main(String[] args) {
//        System.out.println(VM.current().details());   //VM详细信息

//        System.out.println(VM.current().objectAlignment()); //所有对象分配的字节都是8的整数倍

//        Object o = new Object();
//        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Customer customer = new Customer();
        System.out.println(ClassLayout.parseInstance(customer).toPrintable());
    }
}

class Customer {
    int id;
    boolean flag = false;
}
