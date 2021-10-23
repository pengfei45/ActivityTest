package com.fan.activitytest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fan.activitytest.R
import kotlinx.android.synthetic.main.activity_receiver_test.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class ReceiverTestActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver: TimeChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_test)

        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        btn1_receiver_activity.setOnClickListener {
            val intent = Intent("com.fan.activitytest.BootCompleteReceiver")

            val dateStr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")
                current.format(formatter)
            } else {//实测有效
                val date = getCurrentDateTime()
                date.toString("yyyy年MM月dd日 HH:mm:ss")
            }

            intent.putExtra("to_receiver_data",dateStr)
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }


        btn2_receiver_activity.setOnClickListener {
            val intent = Intent("com.fan.activitytest.BootCompleteReceiver")

            val dateStr = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")
                current.format(formatter)
            } else {//实测有效
                val date = getCurrentDateTime()
                date.toString("yyyy年MM月dd日 HH:mm:ss")
            }

            intent.putExtra("to_receiver_data",dateStr)
            intent.setPackage(packageName)
            sendOrderedBroadcast(intent,null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceiver)
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {

        override fun onReceive(p0: Context?, p1: Intent?) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss")
                val formatted = current.format(formatter)

                tv_time_receiver_activity.text = formatted
            } else {//实测有效
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy年MM月dd日 HH:mm:ss")

                tv_time_receiver_activity.text = dateInString
            }
        }

        private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
            val formatter = SimpleDateFormat(format, locale)
            return formatter.format(this)
        }

        private fun getCurrentDateTime(): Date {
            return Calendar.getInstance().time
        }
    }

}