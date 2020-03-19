package com.single.jetpack_demo

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_resouce.*
import kotlin.math.log

class ResourceActivity : AppCompatActivity() {
    companion object {
        val TAG = "ResourceActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resouce)
        val width = resources.displayMetrics.widthPixels
        val height = resources.displayMetrics.heightPixels
        Log.d(TAG, "width:$width")
        Log.d(TAG, "height:$height")
        val density = resources.displayMetrics.density
        Log.d(TAG, "density:$density")
        val dp = dp(30f)
        Log.d(TAG, "dp30:$dp")
        val fl = 30 * density
        Log.d(TAG, "fl:$fl")
    }

    fun dp(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics()
        )
    }
}