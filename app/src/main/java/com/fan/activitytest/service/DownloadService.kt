package com.fan.activitytest.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class DownloadService : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    override fun onCreate() {
        Log.e("DownloadService","Service OnCreated.")

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e("DownloadService","Service onStartCommand.")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("DownloadService","Service onDestroy.")
    }

}