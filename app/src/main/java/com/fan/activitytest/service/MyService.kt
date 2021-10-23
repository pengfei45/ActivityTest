package com.fan.activitytest.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {
    private val mBinder = DownloadBinder()

    override fun onBind(intent: Intent): IBinder {
        return mBinder
    }

    class DownloadBinder : Binder() {
        fun startDownload() {
            Log.e("MyService", "Task startDownload.")
        }

        fun getProgress(): Int {
            Log.e("MyService", "getProgress 开始.")
            return 0
        }
    }
}