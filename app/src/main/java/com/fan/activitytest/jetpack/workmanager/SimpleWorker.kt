package com.fan.activitytest.jetpack.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context,params:WorkerParameters):Worker(context,params) {

    override fun doWork(): Result {
        Log.e("SimpleWorker","在 SimpleWorker 中执行任务。")
        return Result.success()
    }

}