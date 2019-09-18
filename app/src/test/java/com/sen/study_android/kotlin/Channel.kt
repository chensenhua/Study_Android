package com.sen.study_android.kotlin

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.*
import java.util.concurrent.atomic.*

/**
 *  create Data:2019-06-27
 *  create by:chensenhua
 *
 **/

fun main() {
    test1()
    test2()
}

fun test1() {
    runBlocking {
        val channel = Channel<Int>()

        launch {
            for (x in 1..5) channel.send(x)
        }

        repeat(5) {
            println(channel.receive())
        }

        println("done test1")
    }


}

fun test2() {
    runBlocking {
        val channel = Channel<Int>()
        launch {
            for (i in 0..10) {
                channel.send(i);
            }
            delay(3002)
            println("before close")
            channel.close()
        }

        for (y in channel) println("y=" + y)
        println("done")
    }

}


