package com.zxy.zxyhttp.common

import android.widget.Toast
import com.zxy.zxyhttp.utils.tools.ApplicationsTools

object ToastUtils {

    fun showShort(msg: String){
        Toast.makeText(ApplicationsTools.context(),msg,Toast.LENGTH_SHORT).show()
    }
}