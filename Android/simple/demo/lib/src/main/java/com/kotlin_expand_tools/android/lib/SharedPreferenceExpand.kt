package com.kotlin_expand_tools.android.lib

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.bumptech.glide.request.RequestOptions

/**
 * package: com.test.util
 * Author: 杨振宇 1015156849@qq.com
 * Date: 2019/5/20 10:23
 * Description:
 */
open class SharedPreferenceExpand {
    var sharedPreferences: SharedPreferences? = null

    companion object {
        /**双重校验锁式*/
        val instance: SharedPreferenceExpand by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SharedPreferenceExpand()
        }
    }
}

fun <T> Context.getSP(key: String, default: T): T {
    if (SharedPreferenceExpand.instance.sharedPreferences == null) {
        SharedPreferenceExpand.instance.sharedPreferences =
            getSharedPreferences(packageName, Context.MODE_PRIVATE)
    }

    return when (default) {
        is Long -> SharedPreferenceExpand.instance.sharedPreferences?.getLong(key, default)
        is Int -> SharedPreferenceExpand.instance.sharedPreferences?.getInt(key, default)
        is Float -> SharedPreferenceExpand.instance.sharedPreferences?.getFloat(key, default)
        is Boolean -> SharedPreferenceExpand.instance.sharedPreferences?.getBoolean(key, default)
        is String -> SharedPreferenceExpand.instance.sharedPreferences?.getString(key, default)
        else -> default
    } as T

}

fun <T> Context.setSP(key: String, data: T) {

    if (SharedPreferenceExpand.instance.sharedPreferences == null) {
        SharedPreferenceExpand.instance.sharedPreferences =
            getSharedPreferences(packageName, Context.MODE_PRIVATE)
    }

    when (data) {
        is Long -> SharedPreferenceExpand.instance.sharedPreferences?.edit()?.putLong(key, data)
        is Int -> SharedPreferenceExpand.instance.sharedPreferences?.edit()?.putInt(key, data)
        is Float -> SharedPreferenceExpand.instance.sharedPreferences?.edit()?.putFloat(key, data)
        is Boolean -> SharedPreferenceExpand.instance.sharedPreferences?.edit()?.putBoolean(
            key,
            data
        )
        is String -> SharedPreferenceExpand.instance.sharedPreferences?.edit()?.putString(key, data)
        else -> throw TypeCastException("Please convert data with Gson or FastJson first")
    }
    SharedPreferenceExpand.instance.sharedPreferences?.edit()?.apply()
}



