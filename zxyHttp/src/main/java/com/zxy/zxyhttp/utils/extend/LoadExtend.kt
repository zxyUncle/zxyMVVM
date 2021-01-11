package com.zxy.zxyhttp.utils.extend

import android.content.Context
import com.zxy.zxyhttp.utils.tools.LoadTools

/**
 * Created by zsf on 2021/1/11 15:51
 * ******************************************
 * *加载动画扩展
 * ******************************************
 */

fun Context.showLoad(message:String="加载中...") {
    LoadTools.INSTANCE.show(this,message)
}
