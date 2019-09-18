package com.sen.study_android.kotlin

import kotlinx.coroutines.*

/**
 *  create Data:2019-06-27
 *  create by:chensenhua
 *
 **/


suspend fun test() {
    val job = GlobalScope.launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)

            }
        }finally{
            println("handle cancel exception")
        }

    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancel() // 取消该作业
     job.join() // 等待作业执行结束
    println("main: Now I can quit.")




}


fun main() = runBlocking {

    val result= withTimeout(1300L){
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
        "Done"
    }

    println("Result is $result")

   // test();
}
