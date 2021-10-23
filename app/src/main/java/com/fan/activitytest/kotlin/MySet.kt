package com.fan.activitytest.kotlin

class MySet<T>(val helperSet: HashSet<T>) : Set<T> {

    override val size: Int
        get() = helperSet.size

    override fun contains(element: T): Boolean = helperSet.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = helperSet.containsAll(elements)

    override fun isEmpty(): Boolean = helperSet.isEmpty()

    override fun iterator(): Iterator<T> = helperSet.iterator()

}