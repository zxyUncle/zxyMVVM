package com.zxy.zxyhttp.net

import com.zxy.zxyhttp.BuildConfig
import com.zxy.zxyhttp.net.common.gson.MGson
import com.zxy.zxyhttp.utils.tools.LogcatTools
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * Created by zsf on 2021/1/4 14:39
 * ******************************************
 * *
 * ******************************************
 */
object OkHttpService {
    //请求头
    private val mHeaderInterceptoer: Interceptor by lazy { HeaderInterceptoer() }

    // 日志拦截器
    private val mLoggingInterceptor: Interceptor by lazy { LoggingInterceptor() }

    // OkHttpClient客户端
    private val mClient: OkHttpClient by lazy { newClient() }

    // 接口API服务(挂起)
    val api: OkHttpApi by lazy {
        if (BuildConfig.DEBUG)
            createService(OkHttpConfig.HTTP_DEBUG_HOSTURL, OkHttpApi::class.java)
        else
            createService(OkHttpConfig.HTTP_RESEASE_HOSTURL, OkHttpApi::class.java)
    }

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
        connectTimeout(10, TimeUnit.SECONDS)// 连接时间：10s超时-个人觉得超过10的接口都是垃圾接口
        readTimeout(10, TimeUnit.SECONDS)// 读取时间：10s超时
        writeTimeout(10, TimeUnit.SECONDS)// 写入时间：10s超时
        retryOnConnectionFailure(true)
        addNetworkInterceptor(mHeaderInterceptoer)//请求头
        addInterceptor(mLoggingInterceptor)// 日志过滤器
    }.build()

    private class HeaderInterceptoer : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("token", "")//todo 加入token
                .build()
            return chain.proceed(request)
        }
    }

    /**
     * 日志拦截器
     */
    private class LoggingInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            if (request.url.toString().isNotEmpty()) {
                if ("POST" == request.method) {
                    val requestBody = request.body
                    var body = ""
                    val buffer = Buffer()
                    requestBody!!.writeTo(buffer)
                    var charset = Charset.forName("UTF-8")
                    val contentType = requestBody.contentType()
                    if (contentType != null) {
                        charset = contentType!!.charset(Charset.forName("UTF-8"))
                        body = buffer.readString(charset)
                    }
                    LogcatTools.printPost(OkHttpConfig.HTTP_TAG, request.url.toString(), body)
                } else {
                    LogcatTools.printStirng(OkHttpConfig.HTTP_TAG, request.url.toString())
                }
            }
            return chain.proceed(request)
        }
    }
}