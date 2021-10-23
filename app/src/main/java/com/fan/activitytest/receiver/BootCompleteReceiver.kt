package com.fan.activitytest.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.fan.activitytest.MainActivity


class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        if ("com.fan.activitytest.BootCompleteReceiver" == intent.action) {

            val dateStr = intent.getStringExtra("to_receiver_data")
            Toast.makeText(context, "BootCompleteReceiver -- $dateStr", Toast.LENGTH_SHORT).show()

        } else if ("android.intent.action.BOOT_COMPLETED" == intent.action) {
            val bootIntent = Intent(context, MainActivity::class.java)
            // 这里必须为FLAG_ACTIVITY_NEW_TASK
            bootIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(bootIntent)
        }
    }
}
