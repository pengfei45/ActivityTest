package com.fan.activitytest

import android.app.Activity

object ActivityCollector {
    private val activities = ArrayList<Activity>()

    fun addActivity(activity: Activity){
        activities.add(activity)
    }

    fun removeActivity(activity: Activity){
        activities.remove(activity)
    }

    //TODO  activity.isFinishing -> 判断Activity是否处于活跃状态（false）还是等待回收状态（true）。
    fun finishAll(){
        for (activity in activities){
            if (!activity.isFinishing){//活跃状态
                activity.finish()
            }
        }
        activities.clear()
        android.os.Process.killProcess(android.os.Process.myPid())//杀掉当前程序的进程
    }

    fun finishAll1(){
        for (activity in activities){
            if (!activity.isFinishing){//活跃状态
                activity.finish()
            }
        }
        activities.clear()
    }

}