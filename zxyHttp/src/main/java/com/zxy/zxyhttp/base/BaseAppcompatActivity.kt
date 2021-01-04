package com.zxy.zxyhttp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding


/**
 * Created by zsf on 2021/1/4 13:35
 * ******************************************
 * *
 * ******************************************
 */
open abstract class BaseAppcompatActivity<B : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        initView()
        initListener()
    }

    abstract fun getLayoutId(): Int

    open fun initView() {}
    open fun initListener() {}

}