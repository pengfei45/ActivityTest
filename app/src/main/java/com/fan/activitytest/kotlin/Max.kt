package com.fan.activitytest.kotlin

import android.content.Context
import android.view.View
import android.widget.Toast
import com.fan.activitytest.App
import com.fan.activitytest.list.Fruit
import com.google.android.material.snackbar.Snackbar

fun max(vararg nums: Int): Int {
    var maxNum = Int.MIN_VALUE
    for (num in nums) {
        maxNum = kotlin.math.max(maxNum, num)
    }
    return maxNum
}

fun <T : Comparable<T>> max(vararg nums: T): T {
    if (nums.isEmpty()) throw  RuntimeException("Param can not be empty.")
    var maxNum = nums[0]
    for (num in nums) {
        if (num > maxNum) {
            maxNum = num
        }
    }
    return maxNum
}

fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, this, duration).show()
}

fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(App.context, this, duration).show()
}


fun View.showSnackBar(
    text: String, actionText: String? = null,
    duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackBar.setAction(actionText) {
            block()
        }
    }
    snackBar.show()
}

fun View.showSnackBar(
    resId: Int, actionResId: Int? = null,
    duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null
) {
    val snackBar = Snackbar.make(this, resId, duration)
    if (actionResId != null && block != null) {
        snackBar.setAction(actionResId) {
            block()
        }
    }
    snackBar.show()
}

fun main() {
    println(max(8, 12, 9, 2, 99))

    println(max(12.2f, 16f, 95f, 0.98f, 4f, 9562f))
}