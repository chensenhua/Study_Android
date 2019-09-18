package com.sen.study_android.kotlin

import kotlin.system.*
import kotlinx.coroutines.*


/**
 *  create Data:2019-06-28
 *  create by:chensenhua
 *
 **/

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}


fun main() = runBlocking<Unit> {
    val time = measureTimeMillis {
        val one = async {
            doSomethingUsefulOne()
        }

        with(CoroutineScope(coroutineContext+SupervisorJob())){

        }

        val two = async {
            doSomethingUsefulTwo()
        }
        println("The answer is ${one.await() + two.await()}")
    }

    println("Completed in $time ms"+Thread.currentThread().name)
}


