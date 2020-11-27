package com.zxy.zxymvvm.common

import android.widget.Toast
import com.zxy.zxymvvm.common.Applications

object ToastUtils {

    fun showShort(msg: String){
        Toast.makeText(Applications.context(),msg,Toast.LENGTH_SHORT).show()
    }
}