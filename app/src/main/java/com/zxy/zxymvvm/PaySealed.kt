package com.zxy.zxymvvm


/**
 * Created by zsf on 2021/4/28 14:42
 * ******************************************
 * * Kotlin 密封类示例
 * ******************************************
 */
//各个支付需要的参数
data class NETS(var mid: String, var muid: String) : PaySealed()
data class ADYEN(var signRequestInfoBean: String) : PaySealed()
data class DBS(var txnrefId: String) : PaySealed()

sealed class PaySealed {
    //支付的状态
    enum class StatuTypeEnum(var statuType: String, var statuName: String) {
        SUCCESS("succ", "支付成功"),//支付成功
        FAIL("fail", "支付失败"),//支付失败
        INIT("init", "实付中"),//实付中
    }
    //卡的操作类
    object BUILD{
        //去支付、重新支付
        fun toPay(paySealed: PaySealed): Unit = when (paySealed) {
            is NETS -> {
                //NETS去支付方法
            }
            is ADYEN -> {
                //ADYEN去支付方法
            }
            is DBS -> {
                //DBS去支付方法
            }
        }

        //绑卡
        fun bindCard(paySealed: PaySealed): Unit = when (paySealed) {
            is NETS -> {
                //NETS绑卡
            }
            is ADYEN -> {
                //ADYEN绑卡
            }
            is DBS -> {
                //DBS绑卡
            }
        }

        //注销卡
        fun unBindCard(paySealed: PaySealed): Unit = when (paySealed) {
            is NETS -> {
                //NETS绑卡
            }
            is ADYEN -> {
                //ADYEN绑卡
            }
            is DBS -> {
                //DBS绑卡
            }
        }
    }
}




