package com.kotlin_expand_tools.simple.demo

import android.content.Intent
import android.os.Bundle

import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtn1.setOnClickListener {
            startActivity(Intent(this,LanguageActivity::class.java))
        }

        mBtn2.setOnClickListener {
            startActivity(Intent(this,GlideActivity::class.java))
        }



    }
}
