package com.zxy.zxymvvm.activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.zxy.zxyhttp.base.BaseActivity
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.utils.extend.*
import com.zxy.zxyhttp.utils.obj.NavigationObj
import com.zxy.zxymvvm.*
import com.zxy.zxymvvm.activity_viewmodel.VMMainActivity
import com.zxy.zxymvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

/**
 * Created by zsf on 2020/11/19 10:07
 * ******************************************
 * *
 * ******************************************
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var type: String

    //ViewMode
    private val vmMainActivity: VMMainActivity by lazy {
        ViewModelProvider(this)[VMMainActivity::class.java]
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        super.initView()
        AAA()
        vmMainActivity.data.observe(this, {
            showData(it)
        })
        btnRequest.click {
            showLoad()//加载中--，会自动隐藏，放在actiivty可以对Viewmode复用
            vmMainActivity.getData()
        }
        //初始化跳转
        NavigationObj.navInit(this, R.id.mFrameLayout, R.navigation.nav_graph)
        mRadioGroup.check(R.id.rbFirst)

//        startNewActivity<Activity>(bundle {
//            putString("name", "First")
//            putString("age", "12")
//        })

//        val articleData: ArticleData = gson.fromJson("")

        mRadioGroup.setOnCheckedChangeListener { radioGroup, id ->
            when (id) {
                R.id.rbFirst -> {
                    val bundle = bundle {
                        putString("name", "First")
                        putString("age", "12")
                    }
                    //跳转到firstFragment
                    NavigationObj.navSkip(R.id.firstFragment, bundle)
                }
                R.id.rbSecond ->
                    //跳转到secondFragment,动态改变跳转动画
                    NavigationObj.navSkip(
                        R.id.secondFragment,
                        bundle(),
                        NavigationObj.navOptionsExit
                    )
            }
        }
    }

    /**
     * 显示数据
     */
    @SuppressLint("SetTextI18n")
    private fun showData(data: BaseBean<ArrayList<ArticleData>>) {
        binding.articleData = data.data[0]
    }

    private fun AAA() {
        launchIOToMain({
            Log.e("zxy", "123")
            delay(6000)
            Log.e("zxy", "456")
            "123"
        }, {
            Log.e("zxy", "同步主线程")
        }, {
            Log.e("zxy", "异常")
        })
    }
}