package com.fan.providertest

class MySet<T>(val helperSet: HashSet<T>) : Set<T> by helperSet {

    fun helloWorld() = println("Hello World")

    override fun isEmpty() = false

}