package com.zxy.zxyhttp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.zxy.zxyhttp.utils.extend.ZLogger

/**
 * Created by zsf on 2021/1/4 13:50
 * ******************************************
 * *
 * ******************************************
 */
open abstract class BaseFragment<B : ViewDataBinding>:Fragment(), ZLogger {

    lateinit var binding: B

    abstract fun getLayoutId():Int

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, getLayoutId(),container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
        initListener()
    }

    open fun initView(view: View, savedInstanceState: Bundle?){}

    open fun initListener(){}

    override fun onDestroy() {
        super.onDestroy()
        binding.unbind()
    }
}