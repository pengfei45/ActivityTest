package com.fan.activitytest.kotlin

class Money(val value:Int) {

    operator fun plus(money: Money):Money{
        val sum = value + money.value
        return Money(sum)
    }

    operator fun minus(money: Money):Money{
        if (value > money.value){
            return Money(value - money.value)
        }
        return Money(0)
    }

    operator fun plus(newValue:Int):Money{
        val sum = value + newValue
        return Money(sum)
    }

}


fun main() {
    val money1 = Money(11)
    val money2 = Money(10)

    val money3 = money1 + money2
    println(money3.value)

    val money4 = money1 - money2
    println(money4.value)

    val money5 = money3 + 10
    println(money5.value)

}