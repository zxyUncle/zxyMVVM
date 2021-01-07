package com.zxy.zxymvvm.activity

import android.annotation.SuppressLint
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.zxy.zxyhttp.base.BaseAppcompatActivity
import com.zxy.zxyhttp.bean.ArticleData
import com.zxy.zxyhttp.bean.BaseBean
import com.zxy.zxyhttp.utils.extend.*
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.activity_viewmodel.VMMainActivity
import com.zxy.zxymvvm.databinding.ActivityMainBinding
import com.zxy.zxymvvm.fragment.FirstFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zsf on 2020/11/19 10:07
 * ******************************************
 * *
 * ******************************************
 */
class MainActivity : BaseAppcompatActivity<ActivityMainBinding>() {
    private val vmMainActivity: VMMainActivity by lazy {
        ViewModelProvider(this)[VMMainActivity::class.java]
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        super.initView()
        vmMainActivity.data.observe(this, {
            showData(it)
        })
        btnRequest.click {
            vmMainActivity.getData()
        }
        supportFragmentManager.beginTransaction().replace(R.id.mFrameLayout,FirstFragment()).commit()
    }

    /**
     * 显示数据
     */
    @SuppressLint("SetTextI18n")
    private fun showData(data: BaseBean<ArrayList<ArticleData>>) {
        binding.articleData = data.data[0]
    }
}