package com.zxy.zxyhttp.net

/**
 * 网络服务类
 * @author ssq
 */
object NetworkService {
    // 接口API服务(挂起)
    val api:ApiService by lazy { ApiFactory.createService(NetConfigUtils.YN_HOSTURL, ApiService::class.java) }
}