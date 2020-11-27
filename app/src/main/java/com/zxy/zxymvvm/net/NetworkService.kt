package com.example.mvvm.net

import com.zxy.zxymvvm.net.NetConfigUtils

/**
 * 网络服务类
 * @author ssq
 */
object NetworkService {
    // 接口API服务(挂起)
    val api by lazy { ApiFactory.createService(NetConfigUtils.YN_HOSTURL, ApiService::class.java) }
}