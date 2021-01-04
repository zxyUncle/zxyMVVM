package com.zxy.zxymvvm.common

import androidx.lifecycle.viewModelScope
import com.zxy.zxymvvm.bean.LoadState
import com.zxy.zxymvvm.base.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 * ViewModel扩展方法：启动协程
 * @param block 协程逻辑
 * @param onError 错误回调方法
 * @param onComplete 完成回调方法
 */
fun BaseViewModel.reqeust(
    onComplete: suspend (isSuccess: Boolean) -> Unit,
) {
    var baseViewModel:BaseViewModel = this
    this.loadStatus(0)
    var isSuccess = true
    viewModelScope.launch(
        CoroutineExceptionHandler { _, throwable ->
            run {
                // 这里统一处理错误
                ExceptionUtil.catchException(throwable)
            }
        }
    ) {
        isSuccess = try {
            baseViewModel.loadStatus()
            true
        } catch (e: Exception) {
            baseViewModel.loadStatus(1)
            false
        } finally {
            onComplete(isSuccess)
        }
    }
}