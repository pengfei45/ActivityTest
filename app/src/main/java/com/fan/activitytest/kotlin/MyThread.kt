package com.fan.activitytest.kotlin

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import android.os.Message

class MyThread:Runnable {

    override fun run() {
        //具体逻辑
    }
}

fun main() {
    val myThread = MyThread()
    Thread(myThread).start()


    val task =  DownloadAsyncTask()
    task.execute()

    task.cancel(true)
}
val updateText = 1

 val handler:Handler = object:Handler(Looper.getMainLooper()){
     override fun handleMessage(msg: Message) {
         super.handleMessage(msg)
         when(msg.what){
             updateText ->{
                 print("Hello")
             }
         }
     }

 }

class DownloadAsyncTask:AsyncTask<Unit,Int,Boolean>(){


    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: Unit?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)

    }

    override fun onCancelled() {
        super.onCancelled()
    }
}

