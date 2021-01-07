package com.zxy.zxymvvm.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.zxy.zxyhttp.base.BaseFragment
import com.zxy.zxyhttp.utils.extend.click
import com.zxy.zxyhttp.utils.obj.NavigationObj
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.databinding.FragmentFirstBinding
import com.zxy.zxymvvm.fragment_viewmodel.VMFirstFragment
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * Created by zsf on 2021/1/4 13:59
 * ******************************************
 * *
 * ******************************************
 */
class SecondFragment : BaseFragment<FragmentFirstBinding>() {
    private val vmFirstFragment: VMFirstFragment by lazy {
        ViewModelProvider(this)[VMFirstFragment::class.java]
    }

    override fun getLayoutId() = R.layout.fragment_second

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)


    }

    override fun initListener() {
        super.initListener()
        btnHome.click {
            NavigationObj.navSkipFragment(view,R.id.action_secoud_to_home)
        }
    }

}