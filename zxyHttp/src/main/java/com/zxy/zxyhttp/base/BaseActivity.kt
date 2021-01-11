package com.zxy.zxyhttp.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.zxy.zxyhttp.utils.extend.ZLogger
import com.zxy.zxyhttp.utils.obj.ActivityStackManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.MainScope


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
        ActivityStackManager.removeActivity(this)
        binding.unbind()
    }

}