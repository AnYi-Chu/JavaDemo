package juc.jmm;

/**
 * 单例模式
 * （1）构造器私有
 * （2）静态变量保存实例
 * （3）静态方法获取实例
 */
public class Singletion {

    public static void main(String[] args) {
        Singletion1 s1 = Singletion1.INSTANCE;
        Singletion2 s2 = Singletion2.INSTANCE;
        Singletion3 s3 = Singletion3.INSTANCE;
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
    }
}

class Singletion1 { //最简洁饿汉式
    public static final Singletion1 INSTANCE = new Singletion1();

    private Singletion1() {

    }
}

enum Singletion2 {  //枚举饿汉式
    INSTANCE;
}

class Singletion3 { //静态代码块饿汉式
    public static final Singletion3 INSTANCE;

    static {
        INSTANCE = new Singletion3();
    }

    private Singletion3() {

    }
}

class Singletion4 { //线程安全懒汉式
    private static Singletion4 INSTANCE;

    private Singletion4() {

    }

    public static Singletion4 getInstance() {
        if (INSTANCE == null) {
            synchronized (Singletion4.class) {
                INSTANCE = new Singletion4();
            }
        }
        return INSTANCE;
    }
}

class Singletion5 { //内部静态类懒汉式
    private Singletion5() {

    }

    private static class Inner {
        private static final Singletion5 INSTANCE = new Singletion5();  //静态内部类要单独加载和初始化
    }

    public static Singletion5 getInstance() {
        return Inner.INSTANCE;
    }
}
