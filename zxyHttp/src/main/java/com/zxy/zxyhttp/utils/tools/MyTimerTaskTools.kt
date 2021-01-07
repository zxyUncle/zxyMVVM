package com.zxy.zxyhttp.utils.tools

import java.util.*

/**
 * Created by zxy on 2020/7/21 14:17
 * ******************************************
 * *
 * ******************************************
 */
class MyTimerTaskTools : TimerTask() {
    private var maxTime = -1
    private val mTimer: Timer? = Timer()
    private lateinit var onTimerTaskUI: OnTimerTaskUI
    private var mytimerTask: MyTimerTaskTools? = this


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
