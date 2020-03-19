package com.single.jetpack_demo

import android.app.Application

class ThisApp : Application() {
    companion object {
        var instance: ThisApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}