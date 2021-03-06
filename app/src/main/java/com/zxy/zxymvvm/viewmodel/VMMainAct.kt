package com.zxy.zxymvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.net.common.reqeustApi
import com.zxy.zxyhttp.utils.extend.hideLoad

/**
 * Created by zsf on 2020/11/19 17:42
 * ******************************************
 * *
 * ******************************************
 */
class VMMainAct : BaseViewModel() {
    var data = MutableLiveData<BaseBean<ArrayList<ArticleData>>>()

    fun requestData() {

        reqeustApi({ getWXArticle() }, { data.value = it })

    }
}


