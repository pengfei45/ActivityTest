package com.fan.activitytest.jetpack.viewmodel

import androidx.lifecycle.*

class MainViewModel(countReserved: Int) : ViewModel() {

    private val userLiveData = MutableLiveData<User>()

    val userName: LiveData<String> = Transformations.map(userLiveData) {
        "${it.firstName} ${it.lastName}"
    }

    private val userIdLiveData = MutableLiveData<String>()

    val user: LiveData<User> = Transformations.switchMap(userIdLiveData) {
        Repository.getUser(it)
    }

    fun getUser(userId: String) {
        userIdLiveData.value = userId
    }


    val counter: LiveData<Int>
        get() = _counter

    private val _counter = MutableLiveData<Int>()

    init {
        _counter.value = countReserved
    }

    fun plusOne() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun clear() {
        _counter.value = 0
    }

}

data class User(var firstName: String, var lastName: String, var age: Int)