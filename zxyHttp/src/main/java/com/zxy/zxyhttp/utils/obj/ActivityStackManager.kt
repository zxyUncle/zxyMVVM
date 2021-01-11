package com.zxy.zxyhttp.utils.obj

import android.app.Activity

/**
 * Created by zsf on 2021/1/11 14:05
 * ******************************************
 * * 栈管理
 * ******************************************
 */
object ActivityStackManager {

    private val activities = mutableListOf<Activity>()

    fun addActivity(activity: Activity) = activities.add(activity)

    fun removeActivity(activity: Activity) {
        if (activities.contains(activity)) {
            activities.remove(activity)
            activity.finish()
        }
    }

    fun getTopActivity(): Activity? =
        if (activities.isEmpty()) null else activities[activities.size - 1]

    fun finishAll() =
        activities.filterNot { it.isFinishing }.forEach { it.finish() }
}