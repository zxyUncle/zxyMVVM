package com.zxy.zxymvvm.activity_viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.mvvm.base.BaseViewModel
import com.zxy.zxymvvm.bean.ArticleData
import com.example.mvvm.bean.LoadState
import com.example.mvvm.common.launch
import com.zxy.zxymvvm.net.Repository

/**
 * Created by zsf on 2020/11/19 17:42
 * ******************************************
 * *
 * ******************************************
 */
class VMMainActivity : BaseViewModel() {
    var data = MutableLiveData<ArticleData>()
    fun getData(mContext: Context) {
        launch({
            loadState.value = LoadState.Loading()
            data.value = Repository.getWXArticle()
            loadState.value = LoadState.Success()
        }, {
            loadState.value = LoadState.Fail()
        })

    }
}