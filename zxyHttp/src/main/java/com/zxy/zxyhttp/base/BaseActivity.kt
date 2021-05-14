package com.zxy.zxyhttp.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zxy.zxydialog.TToast
import com.zxy.zxyhttp.R
import com.zxy.zxyhttp.base.view.MyToolbar
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
    open fun setToolBar(bar: Toolbar) {
        setToolBarTitle(bar, "")
        setSupportActionBar(bar)
    }

    open fun setToolBar(
        bar: Toolbar,
        title: String?
    ) {
        bar.setTitleTextColor(resources.getColor(R.color.color_333333))
        setToolBarTitle(bar, title)
        setSupportActionBar(bar)
    }


    open fun setToolBarTitle(
        bar: Toolbar,
        title: String?
    ) {
        bar.setTitleTextColor(resources.getColor(R.color.color_333333))
        bar.title = title
    }


    /**
     * 设置为返回按钮
     *
     * @param toolbar
     */
    open fun setToolbarUp(
        toolbar: Toolbar,
        title: String?
    ) {
        setSupportActionBar(toolbar)
        toolbar.title = title
        toolbar.setTitleTextColor(resources.getColor(R.color.color_333333))
        if (toolbar is MyToolbar) {
            (toolbar as MyToolbar).isBackVisible = true
            (toolbar as MyToolbar).setBackTextDrawable(resources.getDrawable(R.mipmap.back))
            (toolbar as MyToolbar).setTitleTextSize(22f)
            (toolbar as MyToolbar).setOnOptionItemClickListener { v ->
                if (v.id == R.id.back) {
                    onBackPressed()
                } else {
                    menuItemClick(v)
                }
            }
        }
    }


    open fun setToolbarHome(
        toolbar: Toolbar,
        title: String?
    ) {
        setSupportActionBar(toolbar)
        toolbar.title = title
        //        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示为返回按钮
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    /**
     * toobar 其他View点击事件
     *
     * @param v
     */
    open fun menuItemClick(v: View?) {}
}