package com.zxy.zxymvvm.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.zxy.zxyhttp.base.BaseFragment
import com.zxy.zxyhttp.utils.extend.click
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.databinding.FragmentFirstBinding
import com.zxy.zxymvvm.viewmodel.VMFirstFrmt
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * Created by zsf on 2021/1/4 13:59
 * ******************************************
 * *
 * ******************************************
 */
class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    private val vmFirstFragment: VMFirstFrmt by lazy {
        ViewModelProvider(this)[VMFirstFrmt::class.java]
    }

    override fun getLayoutId() = R.layout.fragment_first

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
        arguments?.getString("name")?.let { Toast.makeText(activity,"$it", Toast.LENGTH_LONG).show() }

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