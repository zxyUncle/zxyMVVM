package com.zxy.zxyhttp.utils

import java.util.*

/**
 * Created by zsf on 2020/12/30 19:06
 * ******************************************
 * *
 * ******************************************
 */

/**
 * Created by zxy on 2020/7/21 14:17
 * ******************************************
 * *
 * ******************************************
 */
class MyTimerTask : TimerTask() {
    private var maxTime = -1
    private val mTimer: Timer? = Timer()
    private lateinit var onTimerTaskUI: OnTimerTaskUI
    private var mytimerTask: MyTimerTask? = this


    interface OnTimerTaskUI {
        fun onUI(time: Int)
    }

    fun setMaxTime(time: Int) {
        maxTime = time
    }

    override fun run() {
        maxTime -= 1
        onTimerTaskUI.onUI(maxTime)
    }

    /**
     * 开始
     */
    fun start(onTimerTaskUI: OnTimerTaskUI) {
        this.onTimerTaskUI = onTimerTaskUI
        mTimer?.schedule(this, 0, 1000)
    }

    override fun cancel(): Boolean {
        mytimerTask = null
        return super.cancel()
    }


}
