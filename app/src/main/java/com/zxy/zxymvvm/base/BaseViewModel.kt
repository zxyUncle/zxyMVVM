package com.zxy.zxymvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zxy.zxymvvm.bean.LoadState

/**
 * ViewModel 基类
 * @author ssq
 */
abstract class BaseViewModel : ViewModel() {
    // 加载状态
    val loadState = MutableLiveData<LoadState>()

    var isSHowLoad = false //是否显示加载动画

    fun loadStatus(type: Int = 2) {
        if (isSHowLoad) {
            when (type) {
                0 -> {
                    this.loadState.value = LoadState.Loading()
                }
                1 -> {
                    this.loadState.value = LoadState.Fail()
                }
                2 -> {
                    this.loadState.value = LoadState.Success()
                }
                else -> {
                    this.loadState.value = LoadState.Success()
                }

            }
        }
    }
}