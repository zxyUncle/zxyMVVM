package com.zxy.zxyhttp.net.common

/**
 * Created by zsf on 2021/1/4 14:39
 * ******************************************
 * * 
 * ******************************************
 */
class ApiException(val errorCode: Int,val msg: String): Throwable(msg)