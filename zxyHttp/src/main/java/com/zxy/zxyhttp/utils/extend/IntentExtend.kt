@file:JvmName("IntentExtend")
@file:JvmMultifileClass
package com.zxy.zxyhttp.utils.extend

import android.app.Activity
import android.content.Intent
import android.os.Bundle

/**
 * Created by zsf on 2021/2/18 14:00
 * ******************************************
 * * Activity 的跳转
 * ******************************************
 */
/**
 * 使用：startNewActivity<Activity>()
 * 或
 * startNewActivity<Activity>(bundle {
 *          putString("name", "First")
 *          putString("age", "12")
 *      })
 */
inline fun <reified T : Activity> Activity.startNewActivity(bundle: Bundle = bundle()) {
    var intent = Intent(this, T::class.java)
    intent.putExtras(bundle)
    startActivity(intent)
}

fun bundle(callback: (Bundle.() -> Unit) = {}): Bundle {
    val bundle = Bundle()
    callback(bundle)
    return bundle
}


