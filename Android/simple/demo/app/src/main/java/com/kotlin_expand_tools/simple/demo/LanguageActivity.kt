package com.kotlin_expand_tools.simple.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.kotlin_expand_tools.android.lib.changeCurrentLocale
import kotlinx.android.synthetic.main.activity_language.*
import java.util.*

class LanguageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        mBtn1.setOnClickListener {
            changeCurrentLocale(Locale.SIMPLIFIED_CHINESE,true)
        }

        mBtn2.setOnClickListener {
            changeCurrentLocale(Locale.ENGLISH,true)
        }

        mBtn3.setOnClickListener {
            changeCurrentLocale(Locale.JAPANESE,true)
        }
    }
}
