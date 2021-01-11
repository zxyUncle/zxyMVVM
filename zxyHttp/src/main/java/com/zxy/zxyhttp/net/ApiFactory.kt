package com.zxy.zxyhttp.net

import com.zxy.zxyhttp.BuildConfig
import com.zxy.zxyhttp.net.common.gson.MGson
import com.zxy.zxyhttp.net.NetConfigUtils.YN_TAG
import com.zxy.zxyhttp.utils.tools.LogcatTools
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Created by zsf on 2021/1/4 14:39
 * ******************************************
 * *
 * ******************************************
 */
object ApiFactory {
    // 日志拦截器
    private val mLoggingInterceptor: Interceptor by lazy { LoggingInterceptor() }
    // OkHttpClient客户端
    private val mClient: OkHttpClient by lazy { newClient() }

    /**
     * 创建API Service接口实例
     */
    fun <T> createService(baseUrl: String, clazz: Class<T>): T =
        Retrofit.Builder().baseUrl(baseUrl).client(mClient)
            .addConverterFactory(GsonConverterFactory.create(MGson.getInstance()))
            .build().create(clazz)

    /**
     * OkHttpClient客户端
     */
    private fun newClient(): OkHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(30, TimeUnit.SECONDS)// 连接时间：30s超时
        readTimeout(10, TimeUnit.SECONDS)// 读取时间：10s超时
        writeTimeout(10, TimeUnit.SECONDS)// 写入时间：10s超时
        if (BuildConfig.DEBUG) addInterceptor(mLoggingInterceptor)// 仅debug模式启用日志过滤器
    }.build()

    /**
     * 日志拦截器
     */
    private class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val response = chain.proceed(request)
            val headerDate = response.header("Date", "")//请求头
            if (request.url.toString().isNotEmpty())
                if ("POST" == request.method) {
                    var requestBody = request.body
                    var body = ""
                    val buffer = Buffer()
                    requestBody?.writeTo(buffer)
                    var charset: Charset? = Charset.forName("UTF-8")
                    val contentType = requestBody?.contentType()
                    contentType?.let {
                        charset = contentType.charset(Charset.forName("UTF-8"))
                    }
                    body = buffer.readString(charset!!)
                    LogcatTools.printPost(YN_TAG,"${response.code}    ${request.url}",body)
                } else {
                    LogcatTools.printGet(YN_TAG, "$headerDate","${response.code}    ${request.url}")
                }

            return response
        }
    }
}