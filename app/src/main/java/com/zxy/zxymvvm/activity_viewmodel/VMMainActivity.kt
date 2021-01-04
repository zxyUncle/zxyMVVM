package com.zxy.zxymvvm.activity_viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.zxy.zxyhttp.base.BaseViewModel
import com.zxy.zxyhttp.bean.ArticleData
import com.zxy.zxyhttp.bean.BaseBean
import com.zxy.zxyhttp.common.reqeust
import com.zxy.zxyhttp.net.Repository

/**
 * Created by zsf on 2020/11/19 17:42
 * ******************************************
 * *
 * ******************************************
 */
class VMMainActivity : BaseViewModel() {
    var data = MutableLiveData<BaseBean<ArrayList<ArticleData>>>()
    fun getData(mContext: Context) {
        isSHowLoad=true
        reqeust {
            data.value = Repository.getWXArticle()
        }

    }
}