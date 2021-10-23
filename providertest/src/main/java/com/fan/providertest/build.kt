package com.fan.providertest

fun <T> T.build(block: T.() -> Unit): T {
    block()
    return this
}