package jvm;

/**
 * 变量到操作数栈：iload,iload_,lload,lload_,fload,fload_,dload,dload_,aload,aload_
 * 操作数栈到变量：istore,istore_,lstore,lstore_,fstore,fstore_,dstore,dstor_,astore,astore_
 * 常数到操作数栈：bipush,sipush,ldc,ldc_w,ldc2_w,aconst_null,iconst_ml,iconst_,lconst_,fconst_,dconst_
 * 加：iadd,ladd,fadd,dadd
 * 减：isub,lsub,fsub,dsub
 * 乘：imul,lmul,fmul,dmul
 * 除：idiv,ldiv,fdiv,ddiv
 * 余数：irem,lrem,frem,drem
 * 取负：ineg,lneg,fneg,dneg
 * 移位：ishl,lshr,iushr,lshl,lshr,lushr
 * 按位或：ior,lor
 * 按位与：iand,land
 * 按位异或：ixor,lxor
 * 类型转换：i2l,i2f,i2d,l2f,l2d,f2d(放宽数值转换)
 * i2b,i2c,i2s,l2i,f2i,f2l,d2i,d2l,d2f(缩窄数值转换)
 * 创建类实例：new
 * 创建新数组：newarray,anewarray,multianwarray
 * 访问类的域和类实例域：getfield,putfield,getstatic,putstatic
 * 把数据装载到操作数栈：baload,caload,saload,iaload,laload,faload,daload,aaload
 * 从操作数栈存存储到数组：bastore,castore,sastore,iastore,lastore,fastore,dastore,aastore
 * 获取数组长度：arraylength
 * 检相类实例或数组属性：instanceof,checkcast
 * 操作数栈管理：pop,pop2,dup,dup2,dup_xl,dup2_xl,dup_x2,dup2_x2,swap
 * 有条件转移：ifeq,iflt,ifle,ifne,ifgt,ifge,ifnull,ifnonnull,if_icmpeq,if_icmpene,
 * if_icmplt,if_icmpgt,if_icmple,if_icmpge,if_acmpeq,if_acmpne,lcmp,fcmpl
 * fcmpg,dcmpl,dcmpg
 * 复合条件转移：tableswitch,lookupswitch
 * 无条件转移：goto,goto_w,jsr,jsr_w,ret
 * 调度对象的实便方法：invokevirtual
 * 调用由接口实现的方法：invokeinterface
 * 调用需要特殊处理的实例方法：invokespecial
 * 调用命名类中的静态方法：invokestatic
 * 方法返回：ireturn,lreturn,freturn,dreturn,areturn,return
 * 异常：athrow
 * finally关键字的实现使用：jsr,jsr_w,ret
 */
public class InstructionDemo {
    public static void main(String[] args) {

    }
}
