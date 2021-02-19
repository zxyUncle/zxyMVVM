package com.zxy.zxyhttp.base

/**
 * Created by zsf on 2021/2/18 17:10
 * ******************************************
 * *
 * ******************************************
 */

enum class ServerEnum(var statuType:String) {
    SUCCESS("succ"),//支付成功
    FAIL("fail"),//支付失败
    INIT("init"),//实付中
}
