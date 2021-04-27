package com.zxy.zxyhttp.utils.tools.eventbus

import org.greenrobot.eventbus.EventBus

object EventBusTools {

    const val EVENT_TOKEN_OVERDUE = "token过期"
    fun sendToken(){
        EventBus.getDefault().post(MessageEventBean(EVENT_TOKEN_OVERDUE))
    }


}