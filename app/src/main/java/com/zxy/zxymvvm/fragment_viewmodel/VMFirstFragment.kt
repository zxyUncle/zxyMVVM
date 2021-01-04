package com.zxy.zxymvvm.fragment_viewmodel

import androidx.lifecycle.MutableLiveData
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.bean.ArticleData
import com.zxy.zxyhttp.bean.BaseBean
import com.zxy.zxyhttp.common.reqeustApi

/**
 * Created by zsf on 2021/1/4 14:12
 * ******************************************
 * *
 * ******************************************
 */
class VMFirstFragment : BaseViewModel() {
    var data = MutableLiveData<BaseBean<ArrayList<ArticleData>>>()
    fun getData() {
        reqeustApi({
            data.value = it.getWXArticle()
        })
    }
}