package com.single.jetpack_demo.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.single.jetpack_demo.respository.CollectRespository
import com.single.jetpack_demo.viewmodel.CollectViewModel

class CollectViewModelFactory(private val collectRespository: CollectRespository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CollectViewModel(
            collectRespository
        ) as T
    }
}