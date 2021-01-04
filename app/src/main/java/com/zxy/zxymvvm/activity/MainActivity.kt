package com.zxy.zxymvvm.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.activity_viewmodel.VMMainActivity
import com.zxy.zxymvvm.bean.ArticleData
import com.zxy.zxymvvm.bean.BaseBean
import com.zxy.zxymvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zsf on 2020/11/19 10:07
 * ******************************************
 * *
 * ******************************************
 */
class MainActivity : AppCompatActivity() {     // 1
    lateinit var binding: ActivityMainBinding
    private val vmMainActivity: VMMainActivity by lazy {
        ViewModelProvider(this)[VMMainActivity::class.java]
    }
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        vmMainActivity.data.observe(this, {
            showData(it)
        })
        btnRequest.setOnClickListener {
            vmMainActivity.getData(this)
        }
    }

    /**
     * 显示数据
     */
    @SuppressLint("SetTextI18n")
    private fun showData(data: BaseBean<ArrayList<ArticleData>>) {
        if (data.data.isEmpty()) return
        binding.articleData = data.data[0]
    }
}