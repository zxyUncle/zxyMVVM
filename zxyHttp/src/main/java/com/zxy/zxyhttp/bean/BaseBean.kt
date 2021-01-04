package com.zxy.zxyhttp.bean

/**
 * Created by zsf on 2021/1/4 11:20
 * ******************************************
 * *
 * ******************************************
 */
data class BaseBean<T>(
    var errorCode: Int = -1,
    var errorMsg: String = "",
    var data: T
)