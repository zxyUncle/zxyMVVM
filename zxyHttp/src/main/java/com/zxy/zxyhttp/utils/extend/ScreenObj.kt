@file:JvmName("ScreenObj")
@file:JvmMultifileClass
package com.zxy.zxyhttp.utils.extend

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowManager
import android.widget.LinearLayout
import com.zxy.zxyhttp.utils.tools.ApplicationsTools

import java.io.File
import java.io.FileOutputStream

/**
 * Created by zsf on 2021/1/11 14:59
 * ******************************************
 * * 屏幕工具类，涉及到屏幕宽度、高度、密度比、(像素、dp、sp)之间的转换等。
 * ******************************************
 */

/**
 * dip转换为px大小
 *
 * @param context 应用程序上下文
 * @param dpValue dp值
 * @return 转换后的px值
 */
fun dp2px(dpValue: Int): Int {
    return (dpValue * getDensity() + 0.5f).toInt()
}

/**
 * px转换为dp值
 *
 * @param context 应用程序上下文
 * @param pxValue px值
 * @return 转换后的dp值
 */
fun px2dp(pxValue: Int): Int {
    return (pxValue / getDensity() + 0.5f).toInt()
}

/**
 * sp转换为px
 *
 * @param context 应用程序上下文
 * @param spValue sp值
 * @return 转换后的px值
 */
fun sp2px(spValue: Int): Int {
    return (spValue * getScaledDensity() + 0.5f).toInt()
}

/**
 * px转换为sp
 *
 * @param context 应用程序上下文
 * @param pxValue px值
 * @return 转换后的sp值
 */
fun px2sp(pxValue: Int): Int {
    return (pxValue / getScaledDensity() + 0.5f).toInt()
}

/**
 * 获取屏幕宽度，单位为px
 *
 * @param context 应用程序上下文
 * @return 屏幕宽度，单位px
 */
fun getScreenWidth(context: Context? = ApplicationsTools.context()): Int {
    return getDisplayMetrics(context).widthPixels
}

/**
 * 获取屏幕高度，单位为px
 *
 * @param context 应用程序上下文
 * @return 屏幕高度，单位px
 */
fun getScreenHeight(context: Context? = ApplicationsTools.context()): Int {
    return getDisplayMetrics(context).heightPixels
}

/**
 * 获取系统dp尺寸密度值
 *
 * @param context 应用程序上下文
 * @return
 */
fun getDensity(context: Context? = ApplicationsTools.context()): Float {
    return getDisplayMetrics(context).density
}

/**
 * 获取系统字体sp密度值
 *
 * @param context 应用程序上下文
 * @return
 */
fun getScaledDensity(context: Context? = ApplicationsTools.context()): Float {
    return getDisplayMetrics(context).scaledDensity
}

/**
 * 获得状态栏的高度
 *
 * @param context
 * @return
 */
fun getStatusHeight(): Int {
    var statusHeight = -1
    try {
        val clazz = Class.forName("com.android.internal.R\$dimen")
        val `object` = clazz.newInstance()
        val height =
            Integer.parseInt(clazz.getField("status_bar_height").get(`object`)!!.toString())
        statusHeight = ApplicationsTools.context().resources.getDimensionPixelSize(height)
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return statusHeight
}

/**
 * 获取当前屏幕截图，包含状态栏
 *
 * @param activity
 * @return
 */
fun snapShotWithStatusBar(activity: Activity): Bitmap? {
    val decorView = activity.window.decorView
    decorView.isDrawingCacheEnabled = true
    decorView.buildDrawingCache()
    val bmp = decorView.drawingCache
    val width = getScreenWidth(activity)
    val height = getScreenHeight(activity)
    var bitmap: Bitmap? = null
    bitmap = Bitmap.createBitmap(bmp, 0, 0, width, height)
    decorView.destroyDrawingCache()
    return bitmap
}

fun captureScreenWindow(activity: Activity): Bitmap {
    activity.window.decorView.isDrawingCacheEnabled = true
    return activity.window.decorView.drawingCache
}


/**
 * 获取当前屏幕截图，不包含状态栏
 *
 * @param activity
 * @return
 */
fun snapShotWithoutStatusBar(activity: Activity): Bitmap? {
    val decorView = activity.window.decorView
    decorView.isDrawingCacheEnabled = true
    decorView.buildDrawingCache()
    val bmp = decorView.drawingCache
    val frame = Rect()
    activity.window.decorView.getWindowVisibleDisplayFrame(frame)
    val statusHeight = frame.top

    val width = getScreenWidth(activity)
    val height = getScreenHeight(activity)
    var bitmap: Bitmap? = null
    bitmap = Bitmap.createBitmap(bmp, 0, statusHeight, width, height - statusHeight)
    decorView.destroyDrawingCache()
    return bitmap
}

fun screenshotByLinearLayout(linearLayout: LinearLayout): Bitmap {
    var h = 0
    val bitmap: Bitmap
    for (i in 0 until linearLayout.childCount) {
        val childView = linearLayout.getChildAt(i)
        val layoutParams = childView.layoutParams as LinearLayout.LayoutParams
        h += childView.height + layoutParams.topMargin + layoutParams.bottomMargin
    }
    // 创建对应大小的bitmap
    bitmap = Bitmap.createBitmap(
        linearLayout.width, h,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    linearLayout.draw(canvas)
    return bitmap
}

fun screenshotByView(view: View): Bitmap {
    val bitmap: Bitmap
    // 创建对应大小的bitmap
    bitmap = Bitmap.createBitmap(
        view.width, view.height,
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)
    view.draw(canvas)
    return bitmap
}


/**
 * 获取DisplayMetrics对象
 *
 * @param context 应用程序上下文
 * @return
 */
fun getDisplayMetrics(context: Context?): DisplayMetrics {
    val manager = context!!.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val metrics = DisplayMetrics()
    manager.defaultDisplay.getMetrics(metrics)
    return metrics
}

/**
 * @param bmp      获取的bitmap数据
 * @param mContext 上下文
 */
fun saveBmp2Gallery(bmp: Bitmap, mContext: Context) {

    //系统相册目录
    val galleryPath = (Environment.getExternalStorageDirectory().toString()
            + File.separator + Environment.DIRECTORY_DCIM
            + File.separator + "Camera")

    val parentFile = File(galleryPath)
    if (!parentFile.exists()) {
        parentFile.mkdir()
    }
    // 声明文件对象
    var file: File? = null
    // 声明输出流
    var outStream: FileOutputStream? = null
    val picName = "moyu_" + System.currentTimeMillis() / 1000
    try {
        // 如果有目标文件，直接获得文件对象，否则创建一个以filename为名称的文件
        file = File(parentFile, "$picName.jpg")

        // 获得输出流，如果文件中有内容，追加内容
        outStream = FileOutputStream(file)
        if (null != outStream) {
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outStream)
        }
        outStream.flush()
        outStream.close()
    } catch (e: Exception) {
        e.stackTrace
    }

    //通知相册更新
    MediaStore.Images.Media.insertImage(
        mContext.contentResolver,
        bmp,
        file!!.absolutePath,
        null
    )
    val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
    val uri = Uri.fromFile(file)
    intent.data = uri
    mContext.sendBroadcast(intent)

    //ToastUtil.show("图片保存成功");

}

fun setBackgroundAlpha(activity: Activity, bgAlpha: Float) {
    val lp = activity.window
        .attributes
    lp.alpha = bgAlpha//设置透明度（这是窗体本身的透明度，非背景）
    lp.dimAmount = bgAlpha//设置灰度
    if (bgAlpha == 1f) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
    } else {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)//此行代码主要是解决在华为 红米米手机上半透明效果无效的bug
    }
    activity.window.attributes = lp
}

/**
 * 获取是否有虚拟按键
 * 通过判断是否有物理返回键反向判断是否有虚拟按键
 * @param context
 * @return
 */
fun checkDeviceHasNavigationBar(context: Context): Boolean {

    val hasMenuKey = ViewConfiguration.get(context)
        .hasPermanentMenuKey()
    val hasBackKey = KeyCharacterMap
        .deviceHasKey(KeyEvent.KEYCODE_BACK)
    return !hasMenuKey and !hasBackKey
}

//获取虚拟按键的高度
fun getNavigationBarHeight(context: Context): Int {
    var result = 0
    if (checkDeviceHasNavigationBar(context)) {
        val res = context.resources
        val resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId)
        }
    }
    return result
}

