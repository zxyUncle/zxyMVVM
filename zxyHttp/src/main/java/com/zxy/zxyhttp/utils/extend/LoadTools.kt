package com.zxy.zxyhttp.utils.extend

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD
import com.zxy.zxyhttp.R

/**
 * Created by zxy on 2020/8/6 16:06
 * ******************************************
 * *
 * ******************************************
 */
class LoadTools {
    var kProgressHUD: KProgressHUD? = null

    //zxy 单例模式
    companion object {
        val INSTANCE: LoadTools by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LoadTools()
        }
    }

    fun show(mContext: Context,message:String) {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(message)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .show()
        } else {
            if (!kProgressHUD!!.isShowing) {
                hide()
            }
        }

    }

    fun hide() {
        if (kProgressHUD != null && kProgressHUD!!.isShowing) {
            kProgressHUD?.dismiss()
            kProgressHUD = null
        }
    }


}