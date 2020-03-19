package com.single.jetpack_demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimerViewmodel : ViewModel() {
    val seek = MutableLiveData<Int>()
    var time: Int? = null
}