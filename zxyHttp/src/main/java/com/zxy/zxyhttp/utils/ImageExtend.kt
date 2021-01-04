package com.zxy.zxyhttp.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.zxy.zxyhttp.R
import com.zxy.zxyhttp.common.Applications

/**
 * Created by zxy on 2020/7/22 13:58
 * ******************************************
 * * 图片加载扩展函数
 * ******************************************
 */
fun ImageView.loadUrl(img: String="") {
    Glide.with(Applications.context()).load(img).into(this)
}