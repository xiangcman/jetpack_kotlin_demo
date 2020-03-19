package com.single.jetpack_demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.jetpack_demo.db.Collect
import com.single.jetpack_demo.db.History
import com.single.jetpack_demo.respository.PageRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PageViewModel(private val pageRespository: PageRespository) : ViewModel() {
    val datas = pageRespository.getGank()

    fun retry() = datas.retry.invoke()

    val collect = MutableLiveData<Boolean>()
    val addHistory = MutableLiveData<Boolean>()

    val pages = datas.pageList
    fun refresh() {
        datas.refresh.invoke()
    }

    fun addCollect(collect: Collect) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                this@PageViewModel.collect.value = true
            }
            val collectResult = pageRespository.addCollect(collect)
            withContext(Dispatchers.Main) {
                if (collectResult > 0) {
                    this@PageViewModel.collect.value = false
                }
            }
        }
    }


    fun addHistory(history: History) {
        viewModelScope.launch(Dispatchers.IO) {
            pageRespository.addHistory(history)
        }
    }

    val netWorkStatus = datas.netWorkStatus
}