package com.fan.activitytest.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val countReserved:Int):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(countReserved) as T
    }
}