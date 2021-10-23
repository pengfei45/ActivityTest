package com.fan.activitytest.kotlin

inline fun printString(str: String, block: (String) -> Unit) {
    println("printString begin.")
    block(str)
    println("printString end.")
}

inline fun runRunnable(crossinline block:() -> Unit){
    val runnable = Runnable {
        block()
    }
    runnable.run()
}

fun main() {
    println("main start.")
    val str = ""
    printString(str) { s ->
        println("lambda start.")
        if (s.isEmpty()) return
        println(s)
        println("lambda end.")
    }
    println("main end.")
}