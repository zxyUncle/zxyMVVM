package com.zxy.zxymvvm.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.activity_viewmodel.VMMainActivity
import com.zxy.zxymvvm.bean.ArticleData
import com.zxy.zxymvvm.databinding.ActivityMainBinding
import com.zxy.zxymvvm.utils.requestPermission
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermission(
            mutableListOf(
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE
            ), {

            }, {
                Toast.makeText(this, "不同意的: $it", Toast.LENGTH_LONG).show()
            })

        // 2
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
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
    private fun showData(data: ArticleData) {
        if (data.data.isEmpty()) return
        binding.articleData = data
    }
}