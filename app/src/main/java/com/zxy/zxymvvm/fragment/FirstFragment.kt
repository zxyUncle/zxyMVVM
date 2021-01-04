package com.zxy.zxymvvm.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.zxy.zxyhttp.base.BaseFragment
import com.zxy.zxyhttp.bean.ArticleData
import com.zxy.zxyhttp.bean.BaseBean
import com.zxy.zxyhttp.utils.click
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.databinding.FragmentFirstBinding
import com.zxy.zxymvvm.fragment_viewmodel.VMFirstFragment
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by zsf on 2021/1/4 13:59
 * ******************************************
 * *
 * ******************************************
 */
class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    private val vmFirstFragment: VMFirstFragment by lazy {
        ViewModelProvider(this)[VMFirstFragment::class.java]
    }

    override fun getLayoutId() = R.layout.fragment_first

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        vmFirstFragment.data.observe(this, {
            binding.articleData = it.data[0]
        })
    }

    override fun initListener() {
        super.initListener()
        btnGet.click {
            vmFirstFragment.getData()
        }
    }

}