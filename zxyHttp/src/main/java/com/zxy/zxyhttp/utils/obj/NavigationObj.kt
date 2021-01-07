package com.zxy.zxyhttp.utils.obj

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

/**
 * Created by zsf on 2020/11/17 16:07
 * ******************************************
 * * navigation-fragment
 * ******************************************
 */
object NavigationObj {
    private lateinit var fragmentActivity: FragmentActivity
    private lateinit var navController: NavController
    private var frameLayout: Int = 0

    /**
     * 初始化NavHostFragment
     * @param nav_graph 配置文件
     * @param frameLayout 占位FrameLayout
     */
    fun navInit(fragmentActivity: FragmentActivity, frameLayout: Int, nav_graph: Int) {
        NavigationObj.fragmentActivity = fragmentActivity
        NavigationObj.frameLayout = frameLayout
        val finalHost = NavHostFragment.create(nav_graph)
        fragmentActivity.supportFragmentManager.beginTransaction()
//        .add(frameLayout,finalHost)
            .replace(frameLayout, finalHost)
            .setPrimaryNavigationFragment(finalHost) // 等价于 xml 中的 app:defaultNavHost="true"
            .commit()
    }

    /**
     * Activity中Fragment跳转
     * @param frameLayout 占位FrameLayout
     * @param bundle 传递参数
     */
    fun navSkip(action: Int, bundle: Bundle? = Bundle()) {
        navController = Navigation.findNavController(fragmentActivity, frameLayout)
        try {
            navController.navigate(action, bundle)
        } catch (e: Exception) {
            Log.e(NavigationObj.javaClass.simpleName,"跳转异常")
        }

    }

    /**
     * Fragment中Fragment跳转
     * @param view getView()
     * @param frameLayout 占位FrameLayout
     * @param bundle 传递参数
     */
    fun navSkipFragment(view: View?, action: Int, bundle: Bundle = Bundle()) {
        try {
            navController = Navigation.findNavController(view!!)
            navController.navigate(action, bundle)
        } catch (e: Exception) {
            Log.e(NavigationObj.javaClass.simpleName,"跳转异常")
        }

    }

    fun navigateUp() {
        navController.navigateUp()
    }
}


