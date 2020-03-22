package com.single.jetpack_demo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.single.jetpack_demo.db.bean.Collect
import com.single.jetpack_demo.respository.CollectRespository
import kotlinx.coroutines.*

class CollectViewModel(private val collectRespository: CollectRespository) :
    ViewModel() {
    val datas = collectRespository.getCollects()

    val delectMutables = MutableLiveData<Boolean>()

    fun deleteCollectItem(collect: Collect) {
        GlobalScope.launch(Dispatchers.Default) {
            val deferred = viewModelScope.async(Dispatchers.IO) {
                collectRespository.delectCollect(collect)
            }
            val await = deferred.await()
            withContext(Dispatchers.Main) {
                delectMutables.value = await > 0
                refresh()
            }
        }
    }

    fun refresh() {
        datas.refresh.invoke()
    }


}