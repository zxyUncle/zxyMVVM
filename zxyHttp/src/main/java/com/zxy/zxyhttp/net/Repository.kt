package com.zxy.zxyhttp.net

import com.zxy.zxyhttp.bean.ArticleData
import com.zxy.zxyhttp.bean.BaseBean
import com.zxy.zxyhttp.common.ApiException

/**
 * 接口资源
 * @author ssq
 */
object Repository {

    /**
     * 预处理数据(错误)
     */
    private fun preprocessData(it: BaseBean<*>): BaseBean<*> {
        it.let {
//            val fieldCode = it!!::class.java.getDeclaredField(NetConfigUtils.YN_STATUS)
//            val fieldMsg = it!!::class.java.getDeclaredField(NetConfigUtils.YN_MSG)
//            fieldCode.isAccessible = true
//            fieldMsg.isAccessible = true
//            val code = fieldCode.get(it)
//            val msg = fieldMsg.get(it)
            when (it.errorCode) {
                NetConfigUtils.YN_SUCC ->//成功
                    return it
                NetConfigUtils.YN_Fail ->//token失效
                    // 抛出接口异常
                    throw ApiException(it.errorCode, it.errorCode.toString())
                else ->
                    // 抛出接口异常
                    throw ApiException(it.errorCode, it.errorCode.toString())
            }
        }

    }

    /**
     * 获取我的信息（主页——我的页面）
     *
     * @param context 跳至登录页的上下文
     */
    suspend fun getWXArticle(): BaseBean<ArrayList<ArticleData>> =
        NetworkService.api.getWXArticle().apply {
            preprocessData(this)
        }
}