package com.kotlin_expand_tools.android.lib

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import java.util.*
import android.app.ActivityManager.RunningTaskInfo
import android.support.v4.content.ContextCompat.getSystemService
import kotlinx.coroutines.runBlocking
import java.lang.reflect.AccessibleObject.setAccessible


/**
 * package: com.test.util
 * Author: 杨振宇 1015156849@qq.com
 * Date: 2019/5/20 10:00
 * Description: 语言扩展
 */

open class LanguageExpand {
    var currentLocale: Locale? = null
     val activityStack:Stack<Activity> = Stack()

    companion object {
        /**双重校验锁式*/
        val instance: LanguageExpand by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LanguageExpand()
        }
    }
}

fun Application.initLanguageExpand() {
    Log.d("TEST", "后执行了扩展的onCreate")
    Log.d("TEST", getCurrentLocale().language)
    registerActivityLifecycleCallbacks(LanguageActivityLifecycleCallbacks())
    getCurrentLocale()
}

fun Context.getCurrentLocale(): Locale {

    if (LanguageExpand.instance.currentLocale == null) {
        LanguageExpand.instance.currentLocale =
            Locale(getSP("LOCALE", Locale.getDefault().language))
    }
    return LanguageExpand.instance.currentLocale!!
}

fun Context.changeCurrentLocale(locale: Locale, save: Boolean) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        resources.configuration.setLocale(locale)
    } else {
        resources.configuration.locale = locale
    }
    resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    LanguageExpand.instance.currentLocale = locale

    if (save) {
        setSP("LOCALE", locale.language)
        LanguageExpand.instance.activityStack.forEach {
            it.recreate()
        }
    }

}

fun Application.onLanguageConfigurationChanged() {
    changeCurrentLocale(getCurrentLocale(), false)
}

class LanguageActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(p0: Activity?) {}

    override fun onActivityResumed(p0: Activity?) {}

    override fun onActivityStarted(p0: Activity?) {}

    override fun onActivityDestroyed(p0: Activity?) {
        LanguageExpand.instance.activityStack.remove(p0)
    }

    override fun onActivitySaveInstanceState(p0: Activity?, p1: Bundle?) {}

    override fun onActivityStopped(p0: Activity?) {}

    override fun onActivityCreated(p0: Activity?, p1: Bundle?) {
        Log.d("TEST", "onActivityCreate => currentLocale = ${p0?.getCurrentLocale()?.language}")
        p0?.changeCurrentLocale(p0.getCurrentLocale(), false)
        LanguageExpand.instance.activityStack.add(p0)
    }
}


