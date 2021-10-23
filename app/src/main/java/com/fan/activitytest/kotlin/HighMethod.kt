package com.fan.activitytest.kotlin

fun highM(func: (String, Int) -> Unit) {
    func("Hello", 123)
}


fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int {
    return operation(num1, num2)
}

fun plus(num1: Int, num2: Int): Int {
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int {
    return num1 - num2
}


fun StringBuilder.build(block: StringBuilder.() -> Unit):StringBuilder{
    block()
    return this
}

inline fun inlineTest(block1:() -> Unit,noinline block2:() ->Unit){

}




fun main() {
    val num1 = 109
    val num2 = 50

    // 1
    val result = num1AndNum2(num1, num2, ::plus)
    val result1 = num1AndNum2(num1, num2, ::minus)
    println("plus = $result , and minus = $result1")

    //2
    val result2 = num1AndNum2(num1, num2) { num1, num2 ->
        num1 + num2
    }

    val result3 = num1AndNum2(num1, num2) { num1, num2 ->
        num1 - num2
    }
    println("plus1 = $result2 , and minus1 = $result3")

    // 3
    val fruits = listOf("Apple", "Banana", "Orange", "Pear")
    val result4 = StringBuilder().build {
        append("start eating ... \n")
        for (fruit in fruits){
            append(fruit).append("\n")
        }
        append("Ate all fruits.")
    }
    println(result4.toString())

}


class HighMethod {
}