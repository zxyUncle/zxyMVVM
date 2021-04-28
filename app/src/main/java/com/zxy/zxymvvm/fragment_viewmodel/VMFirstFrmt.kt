package com.zxy.zxymvvm.fragment_viewmodel

import androidx.lifecycle.MutableLiveData
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.net.common.reqeustApi

/**
 * Created by zsf on 2021/1/4 14:12
 * ******************************************
 * *
 * ******************************************
 */
class VMFirstFrmt : BaseViewModel() {
    var data = MutableLiveData<BaseBean<ArrayList<ArticleData>>>()
    fun getData() {
        reqeustApi({
            getWXArticle()
        }, {
            data.value = it
        })
    }
}