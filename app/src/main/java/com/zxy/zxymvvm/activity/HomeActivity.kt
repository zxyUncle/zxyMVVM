
package com.zxy.zxymvvm.activity

import android.Manifest
import android.content.SharedPreferences
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.zxy.zxydialog.TToast
import com.zxy.zxyhttp.base.BaseActivity
import com.zxy.zxyhttp.utils.extend.requestPermission
import com.zxy.zxymvvm.R
import com.zxy.zxymvvm.adapter.TestAdapter
import com.zxy.zxymvvm.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import java.io.File

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
        adapterText.addData(
            0,
            mutableListOf(
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark1.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark2.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
                "https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b6779368b734d27b9cdfe2e16f39a3f~tplv-k3u1fbpfcp-watermark.image",
            )
        )

    }

    override fun initListener() {
        super.initListener()

        //这四个点击事件可以只实现一个或者两个，支持部分实现
        zToolbar.addOnToolbarListener(OnBack = {
            onBackPressed()
        },OnIvRight2={
            var file2 = File(
                "mnt/sdcard/Download",
                System.currentTimeMillis().toString() + ".jpg"
            )
            try {
                file2.createNewFile()
            }catch (e:Exception){
                e.printStackTrace()
            }

            if(file2.exists()){
                TToast.show("文件创建成功")
            }else{
                TToast.show("文件创建失败")
            }


        })
        requestPermission(
            mutableListOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
            ), {

            }, {
                Toast.makeText(this, "不同意的: $it", Toast.LENGTH_LONG).show()
            })
    }

}
