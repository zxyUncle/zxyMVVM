package com.zxy.zxyhttp.utils

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by zsf on 2020/12/23 10:14
 * ******************************************
 * * 键盘操作类
 * ******************************************
 */
object KeyboardTools {
    /**
     * 判断软键盘是否正在展示
     * @param edit 输入焦点
     */
     fun isSoftShowing(mContext: AppCompatActivity): Boolean {
        //获取当前屏幕内容的高度
        val screenHeight = mContext.window.decorView.height
        //获取View可见区域的bottom
        val rect = Rect()
        mContext.window.decorView.getWindowVisibleDisplayFrame(rect)
        return screenHeight - rect.bottom !== 0
    }
    /**
     * 是否需要隐藏键盘
     * @param edit 输入焦点
     */
     fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val leftTop = intArrayOf(0, 0)
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop)
            val left = leftTop[0]
            val top = leftTop[1]
            return event.y <= top
        }
        return false
    }

    /**
     * 显示键盘
     * @param edit 输入焦点
     */
     fun showInputKeyboad(editText: EditText, mContext: AppCompatActivity) {
        editText.isFocusable = true
        editText.isFocusableInTouchMode = true
        editText.requestFocus()
        val imm: InputMethodManager = mContext.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * 隐藏键盘
     */
     fun hideInputKeyboad(mContext: AppCompatActivity) {
        val imm = mContext.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        val v = mContext.currentFocus
        if (null != v) {
            imm.hideSoftInputFromWindow(v.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    /**
     * 监听键盘的弹出跟隐藏
     */
    fun onSoftKeyBoardChangeListener(activity: AppCompatActivity,onShow:(Int)->Unit,onHide:(Int)->Unit){
        //软键盘状态监听
        val softKeyBoardListener = SoftKeyBoardListeners(activity)
        softKeyBoardListener.setListener(object :SoftKeyBoardListeners.OnSoftKeyBoardChangeListener{
            override fun keyBoardShow(height: Int) {
                onShow(height)
            }

            override fun keyBoardHide(height: Int) {
                onHide(height)
            }

        })
    }
}