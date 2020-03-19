package com.single.jetpack_demo.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import com.single.jetpack_demo.R
import com.single.jetpack_demo.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {
    lateinit var dialogLoadingBinding: DialogLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogLoadingBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.dialog_loading,
            null,
            false
        )
        setContentView(dialogLoadingBinding.root)
        initAttribute()
    }

    fun initAttribute() {
        val attributes = window?.attributes
        attributes?.width = WindowManager.LayoutParams.WRAP_CONTENT

    }

    fun setLoadingText(text: String) {
        dialogLoadingBinding.title = text
    }
}