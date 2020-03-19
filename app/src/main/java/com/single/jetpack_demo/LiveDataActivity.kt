package com.single.jetpack_demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*

class LiveDataActivity : AppCompatActivity() {
    lateinit var liveData: MutableLiveData<String>

    companion object {
        val TAG = "LiveDataActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        liveData = MutableLiveData()
        liveData.observeForever(Observer<String> { value ->
            Log.d(TAG, "value:$value")
        })
        val liveData1 = liveData.map {
            getIntByString(it)
        }
        liveData1.observe(this@LiveDataActivity, Observer {
            Log.d(TAG, "value1:${it.value}")
        })
    }

    fun getIntByString(value: String): MutableLiveData<Int> {
        val mutableLiveData = MutableLiveData<Int>()
        if (value == "onStart") {
            mutableLiveData.value = 11
        } else if (value == "onResume") {
            mutableLiveData.value = 22
        }
        return mutableLiveData
    }

    override fun onStart() {
        super.onStart()
        liveData.value = "onStart"
    }

    override fun onPause() {
        super.onPause()
        liveData.value = "onPause"
    }

    override fun onResume() {
        super.onResume()
        liveData.value = "onResume"
    }

    override fun onDestroy() {
        super.onDestroy()
        liveData.value = "onDestroy"
    }

}