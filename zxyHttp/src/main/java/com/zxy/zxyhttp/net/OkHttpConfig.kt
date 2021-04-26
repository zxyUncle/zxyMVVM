package com.zxy.zxyhttp.net

import android.content.Intent

/**
 * Created by zsf on 2020/11/20 10:41
 * ******************************************
 * * 网路配置
 * ******************************************
 */
object OkHttpConfig {
    //网路请求的配置
    var CODE_SUCC: Int = 0 //成功的Code
    var CODE_FAIL: Int = -1 //失败的Code
    var CODE_Token: Int = 1 //失败的Code

    var HTTP_DEBUG_HOSTURL = "https://www.wanandroid.com/" //测试服务器地址
    var HTTP_RESEASE_HOSTURL = "https://www.wanandroid.com/" //线上服务器地址
    var HTTP_TAG = "zxy" //网路请求数据显示的Logcat 日志过滤TAG

}