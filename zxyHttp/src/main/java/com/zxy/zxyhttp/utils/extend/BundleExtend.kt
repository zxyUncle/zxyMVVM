package com.zxy.zxyhttp.utils.extend

import android.os.Bundle

/**
 * Created by zsf on 2021/1/7 17:17
 * ******************************************
 * * 自定义Bundle扩展
 * ******************************************
 */

fun bundle(callback: (Bundle.() -> Unit) = {}): Bundle {
    val bundle = Bundle()
    callback(bundle)
    return bundle
}
