package com.example.mvvm.net

import android.content.Context
import com.zxy.zxymvvm.bean.ArticleData
import com.example.mvvm.bean.BaseBean
import com.example.mvvm.common.ApiException
import com.zxy.zxymvvm.net.NetConfigUtils

/**
 * 接口资源
 * @author ssq
 */
object Repository {

    /**
     * 预处理数据(错误)
     */
    private fun <T> preprocessData(it: T): T {
        it.let {
            val fieldCode = it!!::class.java.getDeclaredField(NetConfigUtils.YN_STATUS)
            val fieldMsg = it!!::class.java.getDeclaredField(NetConfigUtils.YN_MSG)
            fieldCode.isAccessible = true
            fieldMsg.isAccessible = true
            val code = fieldCode.get(it)
            val msg = fieldMsg.get(it)
            when (code) {
                NetConfigUtils.YN_SUCC ->//成功
                    return it
                NetConfigUtils.YN_Fail ->//token失效
                    // 抛出接口异常
                    throw ApiException(code as Int, msg.toString())
                else ->
                    // 抛出接口异常
                    throw ApiException(code as Int, msg.toString())
            }
        }

    }

    /**
     * 获取我的信息（主页——我的页面）
     *
     * @param context 跳至登录页的上下文
     */
    suspend fun getWXArticle(): ArticleData =
            NetworkService.api.getWXArticle().let {
                preprocessData(it)
            }
}