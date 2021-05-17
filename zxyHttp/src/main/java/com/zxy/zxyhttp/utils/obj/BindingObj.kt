package com.zxy.zxyhttp.utils.obj

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


/**
 * Created by zsf on 2021/5/17 14:00
 * ******************************************
 * * DataBinding 注解
 * ******************************************
 */
object BindingObj {
    /**
     * Adapter中的图片显示，可以不分实现
     * @param imageView 当前View
     * @param imageSrc 图片
     * @param placeHolder 占位图
     * @param error imageSrc错误时，显示的图片
     */
    @JvmStatic
    @BindingAdapter(
        value = ["android:imageUrl", "android:placeHolder", "android:error"],
        requireAll = false
    )
    fun bindImageSrc(imageView: ImageView, imageSrc: String?, placeHolder: Drawable, error: Drawable) {
        Glide.with(imageView)
            .load(imageSrc)
            .placeholder(placeHolder)
            .error(error)
            .into(imageView)
    }
}