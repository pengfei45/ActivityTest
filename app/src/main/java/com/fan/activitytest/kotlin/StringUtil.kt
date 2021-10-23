package com.fan.activitytest.kotlin

import android.annotation.SuppressLint

object StringUtil {//单例类

    fun lettersCount(str: String): Int {
        var count = 0
        for (char in str) {
            if (char.isLetter()) {
                count++
            }
        }
        return count
    }
}


fun main() {
    println(StringUtil.lettersCount("1564gdskjhdsHJKH56454..."))
    println("1564gdskjhdsHJKH56454..dshsh.".lettersCount())
    println("1234567890".reversed())
    println("hello world this is my world".capitalize())
    println(1+2+3+4)
    println("1"+"2"+"3"+"4")
    println("1234567890" * 5)

    println("123".sumBy { it.toInt() })
}