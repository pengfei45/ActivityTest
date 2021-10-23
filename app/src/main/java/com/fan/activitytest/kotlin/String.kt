package com.fan.activitytest.kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import java.lang.StringBuilder

fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}

operator fun String.times(n: Int): String {
    val builder = StringBuilder()
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}

//operator fun String.times(n:Int) = repeat(n)


fun main() {
    val result1 = getGenericType<String>()
    print("result1 is $result1")
}

inline fun <reified T> getGenericType() = T::class.java

inline fun <reified T> Activity.startActivityTest(context: Context, block:Intent.() -> Unit){
    val intent = Intent(context,T::class.java)
    intent.block()
    context.startActivity(intent)
}

