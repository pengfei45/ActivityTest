package com.fan.fanweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.qweather.sdk.view.HeConfig

class App :Application(){

    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

    }

    override fun onCreate() {
        super.onCreate()

        context = applicationContext


    }

}