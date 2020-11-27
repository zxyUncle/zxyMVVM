package com.zxy.zxymvvm.net

import android.content.Intent

/**
 * Created by zsf on 2020/11/20 10:41
 * ******************************************
 * * 网路配置
 * ******************************************
 */
object NetConfigUtils {
    //网路请求的配置
    const val YN_HOSTURL = "https://www.wanandroid.com/"//服务器地址
    const val YN_STATUS = "errorCode"         //成功失败的code或者status，根据服务器不同，变为不同
    const val YN_MSG = "errorMsg"             //失败提示，根据服务器不同，变为不同
    const val YN_TAG = "zxy"         //网路请求数据显示的Logcat 日志过滤TAG
    const val YN_SUCC = 0        //响应成功
    const val YN_Fail = "-255"        //响应token失效回调--如果没有就可以随便填
    var YN_INTENT_LOGIN: Intent? = null  //Token失效自动跳转到指定Activity

}