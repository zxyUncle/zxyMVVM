package com.zxy.zxyhttp.net.common

import androidx.lifecycle.viewModelScope
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.net.ApiService
import com.zxy.zxyhttp.net.NetConfigUtils
import com.zxy.zxyhttp.net.NetworkService
import com.zxy.zxyhttp.utils.extend.LoadTools
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * Created by zsf on 2021/1/4 14:34
 * ******************************************
 * * isShowLoad是否显示加载动画
 * ******************************************
 */
fun BaseViewModel.reqeustApi(onComplete: suspend ApiService.() -> Unit,isShowLoad:Boolean = false) {
    var baseViewModel: BaseViewModel = this
    baseViewModel.isShowLoad = isShowLoad
    baseViewModel.loadStatus(loading)
    viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            run {
                // 这里统一处理错误
                ExceptionUtil.catchException(throwable)
            }
        }
    ) {
        try {
            baseViewModel.loadStatus(loadSucc)
        } catch (e: Exception) {
            baseViewModel.loadStatus(loadFail)
        } finally {
            onComplete(NetworkService.api).apply {
                LoadTools.INSTANCE.hide() //在请求完毕之后，关闭加载中动画
            }
        }
    }
}

/**
 * 根据实际情况处理
 */
fun BaseBean<*>.response(): BaseBean<*> {
    when (errorCode) {
        NetConfigUtils.YN_SUCC ->//成功
            return this
        NetConfigUtils.YN_Fail ->//token失效
            // 抛出接口异常
            throw ApiException(errorCode, errorCode.toString())
        else ->
            // 抛出接口异常
            throw ApiException(errorCode, errorCode.toString())
    }
}