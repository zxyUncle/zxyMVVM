package com.zxy.zxymvvm.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.zxy.zxydialog.TToast
import com.zxy.zxyhttp.base.BaseActivity
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.adapter.TestAdapter
import com.zxy.zxymvvm.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by zsf on 2021/1/7 16:56
 * ******************************************
 * * 通过Navigation从Fragment跳转过来的
 * ******************************************
 */
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private val adapterText: TestAdapter by lazy {
        TestAdapter()
    }

    override fun getLayoutId() = R.layout.activity_home

    override fun initView() {
        super.initView()

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapterText
        adapterText.addData(0, mutableListOf("1", "2", "3", "4"))

    }

    override fun initListener() {
        super.initListener()

        //这四个点击事件可以只实现一个或者两个，支持部分实现
        zToolbar.addOnToolbarListener(onBack = {
            TToast.show("返回1")
        })
    }

}
