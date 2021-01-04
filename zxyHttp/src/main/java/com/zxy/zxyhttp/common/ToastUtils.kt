package com.zxy.zxyhttp.common

import android.widget.Toast

object ToastUtils {

    fun showShort(msg: String){
        Toast.makeText(Applications.context(),msg,Toast.LENGTH_SHORT).show()
    }
}