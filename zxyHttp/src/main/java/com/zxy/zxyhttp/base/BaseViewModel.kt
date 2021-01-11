package com.zxy.zxyhttp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zxy.zxyhttp.net.bean.LoadState

/**
 * Created by zsf on 2021/1/4 14:38
 * ******************************************
 * *
 * ******************************************
 */
abstract class BaseViewModel : ViewModel() {
    // 加载状态- 暂时未用到，根据需求来扩展
    val loadState = MutableLiveData<LoadState>()

    var isShowLoad = false //是否显示加载动画

    var loading: Long = 0x000001
    var loadFail: Long = 0x000002
    var loadSucc: Long = 0x000003

    /**
     * 0:加载中，1加载失败，2加载完成
     */
    fun loadStatus(type: Long = loadSucc) {
        if (isShowLoad) {
            when (type) {
                loading -> {
                    this.loadState.value = LoadState.Loading()
                }
                loadFail -> {
                    this.loadState.value = LoadState.Fail()
                }
                loadSucc -> {
                    this.loadState.value = LoadState.Success()
                }
                else -> {
                    this.loadState.value = LoadState.Success()
                }

            }
        }
    }
}