package com.fan.activitytest.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.fan.activitytest.MainActivity
import com.fan.activitytest.R

class MyForegroundService : Service() {

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

    override fun onCreate() {
        super.onCreate()

        Log.e("MyForegroundService", "onCreate executed")
        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "my_service",
                "前台Service通知",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            manager.createNotificationChannel(channel)
        }

        val intent = Intent(this, ServiceTestActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this,"my_service")
            .setContentTitle("标题：前台服务")
            .setContentText("唧唧复唧唧，木兰当户织")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources,R.drawable.pear_pic))
            .setContentIntent(pi)
            .build()
        startForeground(1,notification)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        return super.onStartCommand(intent, flags, startId)
    }

}