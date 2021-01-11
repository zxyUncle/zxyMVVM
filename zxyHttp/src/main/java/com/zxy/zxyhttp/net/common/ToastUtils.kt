package com.zxy.zxyhttp.net.common

import android.widget.Toast
import com.zxy.zxyhttp.utils.tools.ApplicationsTools
/**
 * Created by zsf on 2021/1/8 12:00
 * ******************************************
 * *
 * ******************************************
 */
object ToastUtils {

    fun showShort(msg: String){
        Toast.makeText(ApplicationsTools.context(),msg,Toast.LENGTH_SHORT).show()
    }
}