package com.fan.activitytest.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.fan.activitytest.BaseActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_service_test.*

class ServiceTestActivity : BaseActivity() {

    lateinit var downloadBinder: MyService.DownloadBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownloadBinder
            downloadBinder.startDownload()
            val progress = downloadBinder.getProgress()
            Log.e("ServiceTestActivity", "进度：$progress")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            TODO("Not yet implemented")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_test)

        btnStartService_service_test.setOnClickListener {
            val intent = Intent(this, DownloadService::class.java)
            startService(intent)   //启动Service
        }

        btnStopService_service_test.setOnClickListener {
            val intent = Intent(this, DownloadService::class.java)
            stopService(intent)   //停止Service
        }

        btnBindService_service_test.setOnClickListener {
            val intent = Intent(this, DownloadService::class.java)
            bindService(intent,connection,Context.BIND_AUTO_CREATE) //绑定 Service
        }

        btnUnBindService_service_test.setOnClickListener {
            unbindService(connection)  //解绑 Service

        }

        btnStartForeService_service_test.setOnClickListener {
            val intent = Intent(this, MyForegroundService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//启动Service
                startService(intent)
            } else{
                startService(intent)
            }
        }

        btnStopForeService_service_test.setOnClickListener {
            val intent = Intent(this, MyForegroundService::class.java)
            stopService(intent)   //停止Service
        }
    }
}