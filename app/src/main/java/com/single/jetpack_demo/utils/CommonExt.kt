package com.single.jetpack_demo.utils

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.single.jetpack_demo.R

inline fun AppCompatActivity.setupToolBar(
    toolbar: Toolbar,
    action: ActionBar.() -> Unit,
    crossinline block: () -> Unit
) {
    toolbar.setTitleTextColor(resources.getColor(R.color.black))
    setSupportActionBar(toolbar)
    supportActionBar?.run {
        action()
    }
    toolbar.setNavigationOnClickListener {
        block.invoke()
    }
}


fun AppCompatActivity.setLightMode() {
    StatusBarUtil.setLightMode(this)
}