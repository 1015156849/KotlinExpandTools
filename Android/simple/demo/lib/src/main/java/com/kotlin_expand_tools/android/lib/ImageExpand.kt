package com.kotlin_expand_tools.android.lib

import android.app.Application
import android.graphics.drawable.Drawable
import android.support.annotation.DrawableRes
import android.support.annotation.IntRange
import android.support.annotation.IntegerRes
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions


/**
 * package: com.test.util
 * Author: 杨振宇 1015156849@qq.com
 * Date: 2019/5/20 15:43
 * Description:
 */
open class ImageExpand {
    var requestOptions: RequestOptions? = null

    companion object {

        /**双重校验锁式*/
        val instance: ImageExpand by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ImageExpand()
        }
    }
}

fun Application.initImageExpand(placeholder: Int, error: Int) {
    ImageExpand.instance.requestOptions = RequestOptions()
        .placeholder(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .format(DecodeFormat.PREFER_RGB_565)//图片模式;
        .error(error)
}

fun RequestBuilder<Drawable>.buildOptions(): RequestBuilder<Drawable> {
    if (ImageExpand.instance.requestOptions == null) {
        throw ExceptionInInitializerError("请先在Application中调用 initImageExpand 初始化")
    }
    return apply(ImageExpand.instance.requestOptions!!)
}

fun RequestBuilder<Drawable>.buildOptions(placeholder: Drawable, error: Drawable): RequestBuilder<Drawable> {

    if (ImageExpand.instance.requestOptions == null) {
        ImageExpand.instance.requestOptions = RequestOptions()
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .format(DecodeFormat.PREFER_RGB_565)//图片模式;
            .error(error)
    }
    return apply(ImageExpand.instance.requestOptions!!)
}

fun RequestBuilder<Drawable>.buildOptions(placeholder: Int, error: Int): RequestBuilder<Drawable> {

    if (ImageExpand.instance.requestOptions == null) {
        ImageExpand.instance.requestOptions = RequestOptions()
            .placeholder(placeholder)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .format(DecodeFormat.PREFER_RGB_565)//图片模式;
            .error(error)
    }
    return apply(ImageExpand.instance.requestOptions!!)
}


fun ImageView.loadImage(url: Any): View {
    Glide.with(context).load(url).buildOptions().into(this)
    return this
}
