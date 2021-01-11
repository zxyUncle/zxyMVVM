package com.zxy.zxymvvm.activity_viewmodel

import androidx.lifecycle.MutableLiveData
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.net.common.reqeustApi
import com.zxy.zxyhttp.utils.extend.LoadTools
import com.zxy.zxyhttp.utils.extend.showLoad

/**
 * Created by zsf on 2020/11/19 17:42
 * ******************************************
 * *
 * ******************************************
 */
class VMMainActivity : BaseViewModel() {
    var data = MutableLiveData<BaseBean<ArrayList<ArticleData>>>()
    fun getData() {
        reqeustApi({
            data.value = getWXArticle()
        })
    }
}


