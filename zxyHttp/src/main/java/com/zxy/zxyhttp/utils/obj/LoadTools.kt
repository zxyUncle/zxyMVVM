package com.zxy.zxyhttp.utils.obj

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * Created by zxy on 2020/8/6 16:06
 * ******************************************
 * *
 * ******************************************
 */

/**
 * Created by zxy on 2020/8/6 16:06
 * ******************************************
 * * 加载中动画
 * ******************************************
 */
object LoadTools {
    private var kProgressHUD: KProgressHUD? = null

    @JvmStatic
    fun show(mContext: Context, message: String) {
        if (kProgressHUD == null) {
            kProgressHUD = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(message)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.1f)
                .setCancellable {
                    hide()
                }
        }
        kProgressHUD.run {
            if (kProgressHUD != null) {
                if(kProgressHUD!!.isShowing){
                    kProgressHUD = null
                    kProgressHUD?.dismiss()
                    show(mContext, message)
                }else{
                    kProgressHUD!!.show()
                }
            }else{
                show(mContext, message)
            }
        }

    }

    @JvmStatic
    fun hide() {
        if (kProgressHUD != null && kProgressHUD!!.isShowing) {
            kProgressHUD?.dismiss()
            kProgressHUD = null
        }
    }

}