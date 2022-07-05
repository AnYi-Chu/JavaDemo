package jvm;

/**
 * 1.GCRoot
 * 跟搜索路径算法，即通过一系列名为“GCRoots”的对象为起点向下搜索，如果一个对象到GCRoots没有任何引用链相连时，该对象不可用并被标记危死亡
 * GCRoots对象可分为虚拟机栈中引用的对象（栈帧中的局部变量区，即局部变量表）、方法区中的类静态属性引用的对象、方法区中常量引用的对象、本地方法栈中JNI（Native方法）引用的对象
 */
public class GCRootDemo {
    public static void main(String[] args) {
        //本地方法栈中JNI（Native方法）引用的对象
        new Thread(() -> {
        }).start();
    }

    private byte[] byteArray = new byte[100 * 1024 * 1024];

    public static void m1() {
        //虚拟机栈中引用的对象，t1
        GCRootDemo t1 = new GCRootDemo();
        System.gc();
        System.out.println("第一次GC完成");
    }

    //方法区中的类静态属性引用的对象，t2
    private static GCRootDemo2 t2;
    //方法区中常量引用的对象，t3（如果加static，就是强引用）
    private static final GCRootDemo3 t3 = new GCRootDemo3();
}

class GCRootDemo2 {
}

class GCRootDemo3 {
}