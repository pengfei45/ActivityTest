package com.fan.activitytest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.MessageQueue
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fan.activitytest.receiver.practice.LoginActivity
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

open class BaseActivity : AppCompatActivity() {

    lateinit var timeChangeReceiver: TimeChangeReceiver
    lateinit var forceReceiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("BaseActivity", javaClass.simpleName)
        ActivityCollector.addActivity(this)

    }

    override fun onResume() {
        super.onResume()

        initReceiver()
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(timeChangeReceiver)
        unregisterReceiver(forceReceiver)
    }


    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    private fun initReceiver(){
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceiver = TimeChangeReceiver()
        registerReceiver(timeChangeReceiver, intentFilter)

        val intentFilter1 = IntentFilter()
        intentFilter1.addAction("com.fan.activitytest.receiver.practice.OFF_LINE")
        forceReceiver = ForceOfflineReceiver()
        registerReceiver(forceReceiver, intentFilter1)
    }

    inner class TimeChangeReceiver : BroadcastReceiver() {

        override fun onReceive(p0: Context?, p1: Intent?) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm")
                val formatted = current.format(formatter)


                if ("2021年05月09日 17:52" == formatted){
                    val intent = Intent("com.fan.activitytest.receiver.practice.OFF_LINE")
                    intent.putExtra("to_force_date",formatted)
                    sendBroadcast(intent)
                }
            } else {//实测有效
                val date = getCurrentDateTime()
                val dateInString = date.toString("yyyy年MM月dd日 HH:mm")


                if ("2021年05月09日 17:52" == dateInString){
                    val intent = Intent("com.fan.activitytest.receiver.practice.OFF_LINE")
                    intent.putExtra("to_force_date",dateInString)
                    sendBroadcast(intent)
                }
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

    //定時强制下綫廣播接收器
    inner class ForceOfflineReceiver:BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {

            val forceDate = p1?.getStringExtra("to_force_date")

            if (p0 != null) {
                AlertDialog.Builder(p0).apply {
                    setTitle("Warning")
                    setMessage("$forceDate ,您已被强制下线。")
                    setCancelable(false)
                    setPositiveButton("OK"){_,_ ->
                        run {
                            ActivityCollector.finishAll1() //销毁所有Activity
                            val intent = Intent(p0, LoginActivity::class.java)
                            p0.startActivity(intent)
                        }
                    }
                    show()
                }
            }
        }
    }


}