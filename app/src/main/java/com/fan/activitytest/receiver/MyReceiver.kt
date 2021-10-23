package com.fan.activitytest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if ("com.fan.activitytest.BootCompleteReceiver" == intent.action) {

            val dateStr = intent.getStringExtra("to_receiver_data")
            Toast.makeText(context, "MyReceiver -- $dateStr", Toast.LENGTH_SHORT).show()
            Log.e("MyReceiver", dateStr)

            abortBroadcast()
        }
    }
}
