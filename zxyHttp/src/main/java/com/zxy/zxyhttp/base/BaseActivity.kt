package com.zxy.zxyhttp.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zxy.zxydialog.TToast
import com.zxy.zxyhttp.utils.extend.ZLogger
import com.zxy.zxyhttp.utils.obj.ActivityStackManager
import com.zxy.zxyhttp.utils.tools.eventbus.EventBusTools
import com.zxy.zxyhttp.utils.tools.eventbus.MessageEventBean
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by zsf on 2021/1/4 13:35
 * ******************************************
 * *
 * ******************************************
 */
open abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(),ZLogger {
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
        ActivityStackManager.addActivity(this)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner=this
        initView()
        initListener()
    }

    abstract fun getLayoutId(): Int

    open fun initView() {}
    open fun initListener() {}

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        ActivityStackManager.removeActivity(this)
        binding.unbind()
    }

    /**
     * Token失效的监听
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: MessageEventBean) {
        if (event.type==EventBusTools.EVENT_TOKEN_OVERDUE){
            //todo token 过期的跳转
            TToast.show("token 过期的处理")
        }
    }

}