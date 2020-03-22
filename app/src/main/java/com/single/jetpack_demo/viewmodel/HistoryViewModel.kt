package com.single.jetpack_demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.single.jetpack_demo.db.bean.History
import com.single.jetpack_demo.respository.HistoryRespository
import com.single.jetpack_demo.utils.Injection

class HistoryViewModel(private val historyRespository: HistoryRespository = Injection.provideHistoryRespository()) :
    ViewModel() {

    fun getAllHistory(): LiveData<List<History>> {
        return historyRespository.getAllHistory()
    }
}