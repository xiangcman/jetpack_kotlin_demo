package com.single.jetpack_demo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.single.jetpack_demo.databinding.ActivityViewmodelBinding
import com.single.jetpack_demo.viewmodel.TimerViewmodel
import kotlinx.android.synthetic.main.activity_viewmodel.*

class ViewModelActivity : AppCompatActivity() {
    companion object {
        val TAG = "ViewModelActivity"
    }

    lateinit var binding: ActivityViewmodelBinding
    var test = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_viewmodel
        )
        val timerViewmodel = ViewModelProviders.of(this).get(TimerViewmodel::class.java)
        //apply的返回值还是当前对象
        binding.apply {
            viewmodel = timerViewmodel
            lifecycleOwner = this@ViewModelActivity
            testValue = test
            viewmodelValue = timerViewmodel.time
        }
        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    Log.d(TAG, "progress:$progress")
                    timerViewmodel.seek.value = progress
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        //这个是用来测试旋转屏幕时变量变为0的button
        test_btn.setOnClickListener {
            val intent = Intent()
            intent.action = "android.intent.action.VIEW"
            val content_url = Uri.parse("http://www.163.com")
            intent.data = content_url
            startActivity(intent)
//            var testValue = binding.testValue
//            testValue?.let {
//                var value = testValue!!
//                value++
//                binding.testValue = value
////                val time = timerViewmodel.time
////                time
//            }
        }
        timerViewmodel.seek.observe(this, Observer {
            timerViewmodel.time = it
            binding.viewmodelValue = timerViewmodel.time
        })
    }
}