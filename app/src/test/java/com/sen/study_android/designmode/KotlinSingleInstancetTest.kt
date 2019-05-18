package com.sen.study_android.designmode

import org.junit.Test

/**
 * 饿汉时
 *
 */
object SingleDemo1 {
    fun play() {

    }
}

/**
 * 懒汉式
 */

class SingleDemo2 private constructor() {
    companion object {
        private var instance: SingleDemo2? = null
            get() {
                if (field == null) {
                    field = SingleDemo2()
                }
                return field;
            }

        fun getMInstance(): SingleDemo2 {
            return instance!!;
        }
    }

}

/**
 * 线程安全饿汉式
 */
class SingleDemo3 private constructor() {
    companion object {
        private var instance: SingleDemo3? = null
            get() {
                if (field == null) {
                    field = SingleDemo3()
                }
                return field;
            }

        @Synchronized
        fun getMInstance(): SingleDemo3 {
            return instance!!;
        }
    }

}

/**
 * 双重校验
 */

class SingleDemo4 private constructor() {
    companion object {
        val instance: SingleDemo4 by lazy {
            SingleDemo4()
        }
    }

}

/**
 * 静态内部类
 */

class SingleDemo5 private constructor() {
    companion object {
        val instance: SingleDemo4= SingleDemo4.instance;

    }

    private object Holder {
        val instance=SingleDemo5();
    }

}


class Test {

    @Test
    fun testa() {
        SingleDemo1.play()
        SingleDemo2.getMInstance();
    }
}