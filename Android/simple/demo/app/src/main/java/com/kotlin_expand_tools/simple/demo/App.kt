package com.kotlin_expand_tools.simple.demo

import android.app.Application
import android.content.res.Configuration
import android.util.Log
import com.kotlin_expand_tools.android.lib.initImageExpand
import com.kotlin_expand_tools.android.lib.initLanguageExpand
import com.kotlin_expand_tools.android.lib.onLanguageConfigurationChanged

/**
 * package: com.test.util
 * Author: 杨振宇 1015156849@qq.com
 * Date: 2019/5/20 10:06
 * Description:
 */
class App : Application() {
    val TAG="TEST"
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG,"先执行了App的onCreate")
        initLanguageExpand()
        initImageExpand(R.mipmap.ic_launcher,R.mipmap.ic_launcher)

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        onLanguageConfigurationChanged()
    }
}