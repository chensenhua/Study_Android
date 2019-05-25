package com.sen.study_android.designmode;

import android.renderscript.Int3;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 饿汉式
 优点：调用效率高，线程安全；
 缺点：没有使用的时候也会占用资源
 */


class InstanceDemo1 {
    static  InstanceDemo1 mInstance=new InstanceDemo1();


    public static InstanceDemo1 getInstance() {
        return mInstance;
    }
}


/**
 * 懒汉式
 * 优点：延时加载，
 * 缺点：线程不安全
 */
class InstaceDemo2{
    static  InstaceDemo2 mInstance;
    List list=new ArrayList();



    public static InstaceDemo2 getInstance() {
        if(mInstance==null){
            mInstance=new InstaceDemo2();
        }
        return mInstance;
    }
}

/**
 * 同步方法的饿汉式，
 * 优点：延时加载，线程安全
 * 缺点：因为加了同步锁导致效率低
 *
 */

class InstanceDemo3{
    static InstanceDemo3 instance;

    public static synchronized InstanceDemo3 getInstance() {
        if(instance==null){
            instance=new InstanceDemo3();
        }
        return instance;
    }
}

/**
 * 双重检测
 * 优点：线程安全，同步方法的饿汉式高
 * 缺点：在JDK1.5以下，因为volatile机制不生效导致线程不安全问题（Android可以忽略）
 */

class InstanceDemo4{
   volatile static  InstanceDemo4 instance;

    public static InstanceDemo4 getInstance() {
        if(instance==null){
            synchronized (InstanceDemo4.class){
                if(instance==null){
                    instance=new InstanceDemo4();
                }
            }
        }
        return instance;
    }
}


/**
 * 内部类实现方式
 * 优点：效率高，线程安全
 */

class InstanceDemo5{
    private static class Holder{
        static  InstanceDemo5 instance=new InstanceDemo5();
    }

    public static InstanceDemo5 getInstance() {
        return Holder.instance;
    }
}

/**
 * 枚举实现方式
 * 优点：效率高，线程安全
 * 缺点：在Android上，枚举类的占用内存大，不建议使用
 */

enum  InstanceDemo6{
    INSTANCE,
}


public class SingleInstanceTest {

    @Test
    public void test() {

    }
}
