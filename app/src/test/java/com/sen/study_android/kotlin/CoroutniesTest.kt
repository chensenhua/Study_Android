package com.sen.study_android.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout

/**
 *  create Data:2019-06-27
 *  create by:chensenhua
 *
 **/
//class CoroutniesTest {
//
//
//    @Test
//    fun testFirstCoroutinesDemo() {
//
//        thread {
//            //创建一个线程
//
//        }
//
//        runBlocking {
//            //堵塞当前线程
//            delay(3000)
//        }
//
//        var job = GlobalScope.launch {
//            // 在后台启动一个新的协程并继续
//            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
//            println("World!-->" + Thread.currentThread().name) // 在延迟后打印输出
//        }
//        println("Hello,-->" + Thread.currentThread().name) // 协程已在等待时主线程还在继续
//
//
//        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
//
//
//    }
//
//    @Test
//    fun testCreate() {
//        runBlocking {
//            repeat(100_000) {
//                // 启动大量的协程
//                launch {
//                    delay(1000L)
//                    print(".")
//                }
//            }
//        }
//    }
//
//
//}


suspend fun main(args: Array<String>) {
    val result= withTimeout(1300L){
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
        "Done"
    }

    println("Result is $result")
}

