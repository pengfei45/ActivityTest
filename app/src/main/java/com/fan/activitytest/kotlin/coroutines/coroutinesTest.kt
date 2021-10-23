package com.fan.activitytest.kotlin.coroutines

import com.fan.activitytest.net.HttpUtil
import kotlinx.coroutines.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


fun main() {
//    1. 最简单用法
//    GlobalScope.launch {
//        println("codes run in coroutine scope.")
//    }

//    2. 借助runBlocking函数让应用程序在协程中所有代码都运行完之后再结束**
//         runBlocking {
//             println("codes run in coroutine scope.")
//             delay(1500)
//             println("codes run in coroutine scope finished.")
//         }

    //使用launch函数创建多个协程
//    runBlocking {
//        launch {
//            println("launch1.")
//            delay(1000)
//            println("launch1 finished.")
//
//        }
//
//        launch {
//            println("launch2.")
//            delay(1000)
//            println("launch2 finished.")
//
//        }
//    }

    //coroutineScope 用法
//    runBlocking {
//        coroutineScope {
//            launch {
//                for (i in 0..10) {
//                    print("$i ")
//                    delay(1000)
//                }
//            }
//        }
//        println("coroutineScope finished.")
//    }
//    println("runBlocking finished.")


    runBlocking {
        val start = System.currentTimeMillis()
        val result1 = async {
            delay(1000)
            5 + 5
        }
        val result2 = async {
            delay(1000)
            5 + 6
        }
        println("result is ${result1.await() + result2.await()}")
        val end = System.currentTimeMillis()
        println("用时：${end - start}")
    }

}


suspend fun printDot() {
    print(".")
    delay(1000)
}