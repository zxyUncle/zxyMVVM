package com.example.mvvm.common

import android.util.Log
import com.zxy.zxymvvm.BuildConfig

/**
 * 日志工具类
 *
 * 具体规则:
 * -Verbose等级的Log，请不要在user版本中出现。
 * -Info、Warn、Error等级的Log禁止作为普通的调试信息使用，这些等级的Log是系统出现问题时候的重要分析线索，如果随意使用，将给Log分析人员带来极大困扰。
 * -Log的tag命名，使用Activity名称或者类、模块的名称，不要出现自己的姓名拼音或其他简称。
 * -Log输出的频率需要控制,例如1s打印一次的Log，尽量只在eng版本使用。
 * @author ssq
 */
object LogUtil{
    const val TAG = "PetPet"

    /**
     * Verbose: 开发调试过程中一些详细信息，不应该编译进产品中，只在开发阶段使用。
     */
    fun v(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.v(TAG, msg)
        }
    }

    /**
     * Verbose: 开发调试过程中一些详细信息，不应该编译进产品中，只在开发阶段使用。
     */
    fun v(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.v(tag, msg)
        }
    }

    /**
     * Info:例如一些运行时的状态信息，这些状态信息在出现问题的时候能提供帮助。
     */
    fun i(tag: String, msg: String) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, msg)
        }
    }

    /**
     * Error: 错误信息
     */
    fun e(msg: String) {
        Log.e(TAG, msg)
    }
}