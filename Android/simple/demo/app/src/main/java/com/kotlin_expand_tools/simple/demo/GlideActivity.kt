package com.kotlin_expand_tools.simple.demo

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListAdapter
import android.widget.SimpleAdapter
import com.kotlin_expand_tools.android.lib.loadImage
import kotlinx.android.synthetic.main.activity_glide.*

class GlideActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide)

        mBtn1.setOnClickListener {
            mImageView.loadImage("http://img2.imgtn.bdimg.com/it/u=2749032785,4112802673&fm=26&gp=0.jpg")
        }
        mBtn2.setOnClickListener {
            mImageView.loadImage(R.mipmap.ic_launcher_round)
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }
}
