package com.single.jetpack_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.single.jetpack_demo.utils.setLightMode

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLightMode()
    }
}