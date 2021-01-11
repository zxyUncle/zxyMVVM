package com.zxy.zxyhttp.net.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by zsf on 2021/1/4 11:32
 * ******************************************
 * *
 * ******************************************
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class ArticleData(
    var courseId: Int? = null, // 13
    var id: Int? = null, // 434
    var name: String? = null, // Gityuan
    var order: Int? = null, // 190013
    var parentChapterId: Int? = null, // 407
    var userControlSetTop: Boolean? = null, // false
    var visible: Int? = null // 1
) : Parcelable