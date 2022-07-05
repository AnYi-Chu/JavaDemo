package juc.atomic;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.LongBinaryOperator;

/*
 * LongAdder分散热点，将value值分散到一个cell数组中，不同线程CAS操作数组的不同槽，最后将各个槽中的变量累加返回
 * Value=Base+∑n|i=0 Cell[i]
 *
 * add()：Cells表为空，尝试用CAS更新base字段，成功退出，失败出现竞争即uncontended是ture，调用longAccumulate()扩容
 *  Cells表为非空，当前线程映射槽为空，出现竞争即uncontended是ture，调用longAccumulate()扩容
 *  Cells表为非空，当前线程映射槽为非空，CAS更新Cell的值，成功返回，失败即uncontended是false，调用longAccumulate()扩容
 * */
public class LongAdderAPIDemo { //累加器
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        longAdder.increment();
        longAdder.increment();
        System.out.println(longAdder.sum());    //sum()返回当前值，但不保证精确

        LongAccumulator longAccumulator = new LongAccumulator(new LongBinaryOperator() {    //构造新方法
            @Override
            public long applyAsLong(long left, long right) {
                return left + right;
            }
        }, 0);  //0初始值
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);
        System.out.println(longAccumulator.get());
    }
}
