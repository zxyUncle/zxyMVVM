package com.zxy.zxymvvm.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.adapter.TestAdapter
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by zsf on 2021/1/7 16:56
 * ******************************************
 * * 通过Navigation从Fragment跳转过来的
 * ******************************************
 */
class HomeActivity : AppCompatActivity() {
    private val adapterText:TestAdapter by lazy {
        TestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = adapterText
        adapterText.addData(0, mutableListOf("1","2","3","4"))
    }
}
