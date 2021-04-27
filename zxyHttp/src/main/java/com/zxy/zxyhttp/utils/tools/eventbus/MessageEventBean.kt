package com.zxy.zxyhttp.utils.tools.eventbus

/**
 * Created by zsf on 2021/4/27 16:22
 * ******************************************
 * * EventBus 消息的类型
 * @param type 类型
 * @param ext 扩展数据，可以用json
 * ******************************************
 */
data class MessageEventBean(var type:String="", var ext:String=""){
}