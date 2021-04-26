package com.zxy.zxyhttp.net.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.net.OkHttpApi
import com.zxy.zxyhttp.net.OkHttpConfig
import com.zxy.zxyhttp.net.OkHttpService
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.utils.extend.gson
import com.zxy.zxyhttp.utils.extend.launchMain
import com.zxy.zxyhttp.utils.tools.LoadTools
import com.zxy.zxyhttp.utils.tools.LogcatTools
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Created by zsf on 2021/1/4 14:34
 * ******************************************
 * * isShowLoad是否显示加载动画
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
                ExceptionUtil.catchException(throwable)
            }
        }
    ) {
        onRequest(OkHttpService.api).run {
            LoadTools.hide() //在请求完毕之后，关闭加载中动画
            LogcatTools.printJson(OkHttpConfig.HTTP_TAG, gson.toJson(this))
            var baseBean = this
            when (baseBean.errorCode) {
                OkHttpConfig.CODE_Token -> {
                    //todo token失效
                }
                else -> {//正常返回
                    onResponse(baseBean)
                }
            }
        }
    }
}