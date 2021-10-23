package com.fan.activitytest.kotlin

import kotlin.reflect.KProperty

class MyClass {
    var p by Delegate()
}

class Delegate {
    var propValue: Any? = null

    /**
     * @param myClass 声明该 Delegate类的委托功能可以在什么类中使用，这里写成MyClass表示仅可在 MyClass类中使用；
     * @param prop KProperty<*> 是 Kotlin中的一个属性操作类，用于获取各种属性相关的值，在当前场景下用不着，但必须在方法参数上声明。
     */
    operator fun getValue(myClass: MyClass, prop: KProperty<*>): Any? {
        return propValue
    }

    operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
        propValue = value
    }
}