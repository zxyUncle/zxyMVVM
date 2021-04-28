package com.zxy.zxyhttp.net.common

import androidx.lifecycle.viewModelScope
import com.zxy.zxydialog.TToast
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.net.OkHttpApi
import com.zxy.zxyhttp.net.OkHttpConfig
import com.zxy.zxyhttp.net.OkHttpService
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.utils.extend.gson
import com.zxy.zxyhttp.utils.extend.hideLoad
import com.zxy.zxyhttp.utils.tools.LogcatTools
import com.zxy.zxyhttp.utils.tools.eventbus.EventBusTools
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch


/**
 * Created by zsf on 2021/1/4 14:34
 * ******************************************
 * * 网路加载扩展方法
 * ******************************************
 */
inline fun <reified T> BaseViewModel.reqeustApi(
    crossinline onRequest: suspend OkHttpApi.() -> BaseBean<T>,
    crossinline onResponse: ((BaseBean<T>) -> Unit) = {}
) {
    viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            run {
                // 这里统一处理错误
                //todo toast 系统错误
                TToast.show("System error")
                LogcatTools.printJson(OkHttpConfig.HTTP_TAG, gson.toJson(throwable))
            }
        }
    ) {
        onRequest(OkHttpService.api).run {
            hideLoad() //在请求完毕之后，关闭加载中动画
            LogcatTools.printJson(OkHttpConfig.HTTP_TAG, gson.toJson(this))
            when (errorCode) {
                OkHttpConfig.CODE_Token -> {
                    //token失效
                    EventBusTools.sendToken()
                }
                else -> {//正常返回
                    onResponse(this)
                }
            }
        }
    }
}