package com.zxy.zxymvvm.activity_viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zxy.zxymvvm.base.BaseViewModel
import com.zxy.zxymvvm.bean.ArticleData
import com.zxy.zxymvvm.bean.LoadState
import com.zxy.zxymvvm.common.reqeust
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
        isSHowLoad=true
        reqeust {
            data.value = Repository.getWXArticle()
        }

    }
}