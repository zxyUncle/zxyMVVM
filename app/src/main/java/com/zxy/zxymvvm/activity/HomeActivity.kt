package com.zxy.zxymvvm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zxy.zxymvvm.R
/**
 * Created by zsf on 2021/1/7 16:56
 * ******************************************
 * * 通过Navigation从Fragment跳转过来的
 * ******************************************
 */
class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}