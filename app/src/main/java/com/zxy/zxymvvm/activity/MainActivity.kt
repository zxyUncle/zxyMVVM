package com.zxy.zxymvvm.activity

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.zxy.zxydialog.TToast
import com.zxy.zxyhttp.base.BaseActivity
import com.zxy.zxyhttp.net.bean.ArticleData
import com.zxy.zxyhttp.net.bean.BaseBean
import com.zxy.zxyhttp.utils.extend.*
import com.zxy.zxyhttp.utils.obj.NavigationObj
import com.zxy.zxymvvm.*
import com.zxy.zxymvvm.viewmodel.VMMainAct
import com.zxy.zxymvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import java.util.concurrent.locks.Lock

/**
 * Created by zsf on 2020/11/19 10:07
 * ******************************************
 * *
 * ******************************************
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var type: String

    //ViewMode
    private val vmMainActivity: VMMainAct by lazy {
        ViewModelProvider(this)[VMMainAct::class.java]
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun initView() {
        super.initView()

        var list = mutableListOf<String>()

        vmMainActivity.data.observe(this,{
            showData(it)
        })
        btnRequest.click {
            showLoad()//显示加载动画
            vmMainActivity.getData()
        }
        lifecycleScope.launch {}

        mToolbar.addToolbarOnClickListener {
            TToast.show("返回")
        }

        //初始化跳转
        NavigationObj.navInit(this, R.id.mFrameLayout, R.navigation.nav_graph)
        mRadioGroup.check(R.id.rbFirst)

        sharedPreferences.edit {
            putString("key", "")
        }
        sharedPreferences.getInt("token", 1)

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