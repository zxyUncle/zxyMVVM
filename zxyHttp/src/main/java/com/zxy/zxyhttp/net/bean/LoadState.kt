package com.zxy.zxyhttp.net.bean

/**
 * Created by zsf on 2021/1/4 14:38
 * ******************************************
 * * 加载动画的密封类
 * ******************************************
 */
sealed class LoadState(val msg: String) {
    /**
     * 加载中
     */
    class Loading(msg: String = ""): LoadState(msg)

    /**
     * 成功
     */
    class Success(msg: String = ""): LoadState(msg)

    /**
     * 失败
     */
    class Fail(msg: String = ""): LoadState(msg)
}