@file:Suppress("UNCHECKED_CAST")

package com.zxy.zxyhttp.utils.extend

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.google.gson.Gson
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.utils.tools.ApplicationsTools

/**
 * Created by zsf on 2021/1/4 20:03
 * ******************************************
 * * 所有保存的数据统一处理
 * ******************************************
 */
//所有的Bean KEY 统一处理
val SharedPreferences.BASE_ArticleData: String get() = "ArticleData"  //基类的Key


//初始化延迟加载，config文件名字
val sharedPreferences: SharedPreferences by lazy {
    ApplicationsTools.context().getSharedPreferences("config", AppCompatActivity.MODE_PRIVATE)
}

/**
 *  普通保存参数示例
 *  sharedPreferences.run {
 *      putString("key","")
 *  }
 */

/**
 *  保存基类示例
 *  sharedPreferences.run {
 *      saveBean(BASE_ArticleData, ArticleData())
 *      getBean<ArticleData>(BASE_ArticleData)
 *  }
 */
fun SharedPreferences.saveBean(key: String, articleData: ArticleData) {
    sharedPreferences.edit {
        putString(key, gson.toJson(articleData))
    }
}

inline fun <reified T> SharedPreferences.getBean(key: String): T {
    return sharedPreferences.run {
        gson.fromJson(getString(key, gson.toJson(ArticleData())), T::class.java)
    }
}




