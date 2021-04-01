@file:JvmName("LoggerExtend")
@file:JvmMultifileClass
package com.zxy.zxyhttp.utils.extend

import android.util.Log
import com.zxy.zxyhttp.BuildConfig
/**
 * Created by zsf on 2021/1/11 14:25
 * ******************************************
 * * 自定义日志
 * ******************************************
 */
interface ZLogger {
    val loggerTag: String get() = getTag(javaClass)
}
private fun getTag(clazz: Class<*>) = clazz.simpleName.let {
    if (it.length <= 23) it else it.substring(0, 23)
}

private fun isDebug() = BuildConfig.DEBUG

fun ZLogger.printString(message: String="") {
    if(isDebug())
    Log.e(loggerTag, "$message")
}


