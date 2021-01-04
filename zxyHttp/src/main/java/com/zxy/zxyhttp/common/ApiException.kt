package com.zxy.zxyhttp.common

/**
 * Created by zsf on 2021/1/4 14:39
 * ******************************************
 * * 
 * ******************************************
 */
class ApiException(val errorCode: Int,val msg: String): Throwable(msg)