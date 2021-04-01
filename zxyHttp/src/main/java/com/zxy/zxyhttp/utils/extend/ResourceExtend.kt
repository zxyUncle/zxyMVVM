@file:JvmName("ResourceExtend")
@file:JvmMultifileClass
package com.zxy.zxyhttp.utils.extend

import android.content.Context
import android.os.Build
import android.text.Html
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat

/**
 * Created by zsf on 2021/1/11 14:47
 * ******************************************
 * * 资源扩展
 * ******************************************
 */
fun Context.stringValue(@StringRes stringRes: Int) = resources.getString(stringRes)
fun Context.stringValue(stringRes: String) = stringRes

fun Context.drawableValue(@DrawableRes drawableRes: Int) = ContextCompat.getDrawable(this, drawableRes)

@Suppress("DEPRECATION")
fun String.renderHtml(): String =
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        Html.fromHtml(this, HtmlCompat.FROM_HTML_MODE_COMPACT).toString()
    else Html.fromHtml(this).toString()