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
fun Array<View>.clicks(click: (View) -> Unit) {
    var views:Array<View> = this
    for (v in views) {
        v?.click {
            click(v)
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

private var <T : View> T.triggerLastTime: Long
    get() = if (getTag(1123460103) != null) getTag(1123460103) as Long else -601
    set(value) {
        setTag(1123460103, value)
    }

private var <T : View> T.triggerDelay: Long
    get() = if (getTag(1123461123) != null) getTag(1123461123) as Long else 600
    set(value) {
        setTag(1123461123, value)
    }

private fun <T : View> T.clickEnable(): Boolean {
    var flag = false
    val currentClickTime = System.currentTimeMillis()
    if (currentClickTime - triggerLastTime >= triggerDelay) {
        flag = true
    }
    triggerLastTime = currentClickTime
    return flag
}