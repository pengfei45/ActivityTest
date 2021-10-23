package com.fan.activitytest.jetpack.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {

    fun getUser(userId:String):LiveData<User>{
        val liveData = MutableLiveData<User>()
        liveData.value = User("FirstName $userId 号","LastName",10)
        return liveData
    }

}