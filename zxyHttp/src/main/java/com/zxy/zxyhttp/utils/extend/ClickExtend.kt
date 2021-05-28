@file:JvmName("ClickExtend")
@file:JvmMultifileClass

package com.zxy.zxyhttp.utils.extend

import android.view.View

/**
 * Created by zxy on 2019/10/12-15:01
 * Class functions
 * ******************************************
 * * 点击事件的扩展-仿抖动
 * ******************************************
 */


/**
 * 多个点击事件
 */
fun <T : View> Array<T>.clicks(time: Long = 300, callback: (View) -> Unit) {
    var views: Array<T> = this
    for (v in views) {
        v?.click(time) {
            callback(v)
        }
    }
}


/***
 * 单个点击事件
 * @param delay Long 延迟时间，默认600毫秒
 * @param block: (T) -> Unit 函数
 * @return Unit
 */
fun <T : View> T.click(time: Long = 300, block: (T) -> Unit) {
    var viewOld: View? = null
    triggerDelay = time
    setOnClickListener {
        if (it == viewOld) {
            if (clickEnable()) {
                viewOld = it
                block(it as T)
            }
        } else {
            clickEnable()
            viewOld = it
            block(it as T)
        }
    }
}

private var triggerLastTime = 0L

private var triggerDelay: Long = 0L


private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}