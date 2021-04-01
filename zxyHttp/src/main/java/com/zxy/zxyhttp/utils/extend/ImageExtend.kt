@file:JvmName("ImageExtend")
@file:JvmMultifileClass
package com.zxy.zxyhttp.utils.extend

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zxy.zxyhttp.R
import com.zxy.zxyhttp.utils.tools.ApplicationsTools

/**
 * Created by zxy on 2020/7/22 13:58
 * ******************************************
 * * 图片加载扩展函数
 * ******************************************
 */
fun ImageView.loadUrl(img: String="") {
    Glide.with(ApplicationsTools.context()).load(img).into(this)
}