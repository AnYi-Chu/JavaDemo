package jvm;

/**
 * GC设置：run->edit Configurations->环境变量
 * 查看运行中的java程序它某个jvm参数是否开启？值？
 * jps（查看java后台进程）
 * jinfo -flag 进程编号（查看正在运行）
 * java -XX:+PrintFlagsInitial（查看JVM初始化，=是默认，:=是改过）
 * java -XX:+PrintFlagsFinal -version（查看修改更新）
 * java -XX:+PrintCommandLineFlags -version
 *
 * <p>
 * JVM参数分为标配参数、X参数、XX参数
 * XX参数分为Boolean类型、KV设值类型
 * Boolean类型公式：-XX:+参数/-XX:-参数，+表示开启，-表示关闭
 * -XX:+PrintGCDetails
 * KV设值类型公式：-XX:属性key=属性值value
 * -XX:MetaspaceSize=128m
 * <p>
 * -Xms等价于-XX:InitialHeapSize
 * -Xmx等价于-XX:MaxHeapSize
 */
public class HelloGC {
    public static void main(String[] args) throws Exception {
        System.out.println("helloGC");
        Thread.sleep(Integer.MAX_VALUE);
    }
}

