package com.zxy.zxyhttp.net

/**
 * Created by zsf on 2021/1/8 12:00
 * ******************************************
 * *
 * ******************************************
 */
object NetworkService {
    // 接口API服务(挂起)
    val api:ApiService by lazy { ApiFactory.createService(NetConfigUtils.YN_HOSTURL, ApiService::class.java) }
}