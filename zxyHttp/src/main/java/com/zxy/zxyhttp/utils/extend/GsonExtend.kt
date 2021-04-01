@file:JvmName("GsonExtend")
@file:JvmMultifileClass
package com.zxy.zxyhttp.utils.extend

import com.google.gson.Gson

/**
 * Created by zsf on 2021/2/18 14:13
 * ******************************************
 * *
 * ******************************************
 */

val gson: Gson by lazy {
    Gson()
}
/**
 * 必须要去写他的类型，通过reified字段去推断类型
 * 用法:
 *      val articleData: ArticleData = gson.fromJson("jsonString")
 * 或者
 *      gson.fromJson<ArticleData>("jsonString")
 */
inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, T::class.java)
