package com.zxy.zxyhttp.net

import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.net.bean.BaseBean
import retrofit2.http.GET

/**
 * Created by zsf on 2021/1/4 14:30
 * ******************************************
 * *
 * ******************************************
 */
@JvmSuppressWildcards
interface ApiService {

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    suspend fun getWXArticle(): BaseBean<ArrayList<ArticleData>>
}