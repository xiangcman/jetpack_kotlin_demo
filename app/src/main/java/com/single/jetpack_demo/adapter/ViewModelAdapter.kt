package com.single.jetpack_demo.adapter

import android.content.DialogInterface
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import com.single.jetpack_demo.web.CommonWebActivity
import java.text.SimpleDateFormat
import java.util.*

object ViewModelAdapter {

    @JvmStatic
    @BindingAdapter("jumpToWeb")
    fun jumpToWeb(view: View, url: String, title: String) {
        val intent = Intent(view.context, CommonWebActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("title", title)
        view.context.startActivity(intent)
    }

    @JvmStatic
    @BindingAdapter("showtext")
    fun bindWateringText(textView: TextView, wateringInterval: Int) {
        textView.text = "$wateringInterval"
    }

    @JvmStatic
    @BindingAdapter("isVisible")
    fun bindIsGone(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    @JvmStatic
    @BindingAdapter("click")
    fun onClick(view: View, action: () -> Unit) {
        view.setOnClickListener {
            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("收藏该篇文章?")
            builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    action.invoke()
                }

            })
            builder.setNegativeButton("取消", null)
            builder.show()
        }
    }

    @JvmStatic
    @BindingAdapter("onLongClick")
    fun onLongClick(view: View, action: () -> Unit) {
        view.setOnLongClickListener {
            val builder = AlertDialog.Builder(view.context)
            builder.setMessage("取消收藏该篇文章?")
            builder.setPositiveButton("确定", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    action.invoke()
                }

            })
            builder.setNegativeButton("取消", null)
            builder.show()
            true
        }
    }

    @JvmStatic
    @BindingAdapter("showTime")
    fun showTime(view: TextView, date: Date) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        view.setText(simpleDateFormat.format(date))
    }
}
