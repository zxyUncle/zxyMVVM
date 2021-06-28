package com.zxy.zxymvvm.activity

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
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

/**
 * Created by zsf on 2020/11/19 10:07
 * ******************************************
 * *
 * ******************************************
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    //ViewMode
    private val vmMainActivity: VMMainAct by lazy {
        ViewModelProvider(this)[VMMainAct::class.java]
    }

    override fun getLayoutId() = R.layout.activity_main

    override fun initListener() {
        super.initListener()

        //这四个点击事件可以只实现一个或者两个，支持部分实现-如HomeActivity
        zToolbar.addOnToolbarListener(OnBack = {
            TToast.show("返回1")
        }, OnIvRight1 = { //可省略
            TToast.show("分享1")
        }, OnIvRight2 = { //可省略
            TToast.show("分享2")
        }, OntvRight = {//可省略
            photo()
        })
    }

    private fun photo() {
        PictureSelector.create(this@MainActivity)
            .openGallery(PictureMimeType.ofImage())//全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
            .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.MULTIPLE or PictureConfig.SINGLE
            .isPreviewImage(false)// 是否可预览图片 true or false
            .isCamera(false)// 是否显示拍照按钮 true or false
            .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
            .isEnableCrop(false)// 是否裁剪 true or false
            .imageEngine(GlideEngine.createGlideEngine())
            .isUseCustomCamera(true)// 是否使用自定义相机
            .isCompress(true)// 是否压缩 true or false
            .hideBottomControls(false)// 是否显示uCrop工具栏，默认不显示 true or false
            .isGif(false)// 是否显示gif图片 true or false
            .freeStyleCropEnabled(false)// 裁剪框是否可拖拽 true or false
            .circleDimmedLayer(false)// 是否圆形裁剪 true or false
            .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
            .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
            .isOpenClickSound(false)// 是否开启点击声音 true or false
            .minimumCompressSize(50)// 小于50kb的图片不压缩
            .rotateEnabled(false) // 裁剪是否可旋转图片 true or false
            .scaleEnabled(true)// 裁剪是否可放大缩小图片 true or false
            .isDragFrame(false)// 是否可拖动裁剪框(固定)
            .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    override fun initView() {
        super.initView()

        vmMainActivity.data.observe(this, {
            hideLoad() //关闭加载中动画
            showData(it)
        })
        btnRequest.click {
            showLoad()//显示加载动画
            vmMainActivity.requestData()
        }
        lifecycleScope.launch {}

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

}